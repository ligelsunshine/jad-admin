/*
 * Copyright (c) 2021-2021, jad (cxxwl96@sina.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jad.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jad.common.base.form.SearchForm;
import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.Dept;
import com.jad.common.entity.Menu;
import com.jad.common.entity.Role;
import com.jad.common.entity.RoleMenu;
import com.jad.common.entity.User;
import com.jad.common.entity.UserRole;
import com.jad.common.exception.BadRequestException;
import com.jad.common.exception.UnauthorizedException;
import com.jad.common.function.PropertyFunc;
import com.jad.common.lang.SearchResult;
import com.jad.common.mapper.UserMapper;
import com.jad.common.model.dto.UserBaseInfoDto;
import com.jad.common.service.DeptService;
import com.jad.common.service.MenuService;
import com.jad.common.service.RoleMenuService;
import com.jad.common.service.RoleService;
import com.jad.common.service.UserRoleService;
import com.jad.common.service.UserService;
import com.jad.common.utils.RedisUtil;
import com.jad.common.utils.ValidatorUtil;
import com.jad.common.valid.AddValidGroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统用户服务类
 *
 * @author cxxwl96
 * @since 2021-06-18
 */
@Slf4j
@Service
// @DS("master")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 是否添加成功
     */
    @Override
    @Transactional
    public boolean save(User user) {
        // 校验
        validate(user, false);
        // 添加用户
        if (!super.save(user)) {
            throw new BadRequestException("添加用户失败");
        }
        // 分配角色
        List<UserRole> userRoles = new ArrayList<>();
        user.getRoleIds().forEach(roleId -> {
            final UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        });
        if (!userRoleService.saveBatch(userRoles)) {
            throw new BadRequestException("分配角色失败");
        }
        return true;
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 是否删除成功
     */
    @Override
    public boolean removeById(String id) {
        // 不能删除超级管理员
        if (hasAdministrator(id)) {
            throw new BadRequestException("不能删除超级管理员");
        }
        // 清除用户授权信息
        final User user = super.getById(id);
        if (user != null) {
            clearUserAuthorityByUsername(user.getUsername());
        }
        // 删除
        PropertyFunc<UserRole, ?> userIdColumn = UserRole::getUserId;
        final Map<String, Object> map = new HashMap<>();
        map.put(userIdColumn.getColumnName(), id);
        if (!userRoleService.removeByMap(map)) {
            throw new BadRequestException("删除用户角色失败");
        }
        if (!super.removeById(id)) {
            throw new BadRequestException("删除失败");
        }
        return true;
    }

    /**
     * 更新用户
     *
     * @param user 用户
     * @return 是否更新成功
     */
    @Override
    public boolean update(User user) {
        // 校验
        validate(user, true);
        if (!super.updateById(user)) {
            throw new BadRequestException("修改用户失败");
        }
        // 分配角色
        // 删除原来分配的角色
        final long count = userRoleService.lambdaQuery().eq(UserRole::getUserId, user.getId()).count();
        if (count > 0) {
            PropertyFunc<UserRole, ?> userIdColumn = UserRole::getUserId;
            final Map<String, Object> map = new HashMap<>();
            map.put(userIdColumn.getColumnName(), user.getId());
            if (!userRoleService.removeByMap(map)) {
                throw new BadRequestException("分配角色失败");
            }
        }
        // 添加新分配的角色
        List<UserRole> userRoles = new ArrayList<>();
        user.getRoleIds().forEach(roleId -> {
            final UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        });
        if (!userRoleService.saveBatch(userRoles)) {
            throw new BadRequestException("分配角色失败");
        }
        // 清除用户授权信息
        clearUserAuthorityByUsername(user.getUsername());
        return true;
    }

    /**
     * 更新当前登录用户基础信息
     *
     * @param userBaseInfoDto 用户
     * @return 是否更新成功
     */
    @Override
    public boolean updateBaseInfo(UserBaseInfoDto userBaseInfoDto) {
        final User user = getCurrentAuthUser();
        BeanUtil.copyProperties(userBaseInfoDto, user);
        if (!super.updateById(user)) {
            throw new BadRequestException("修改用户失败");
        }
        // 清除用户授权信息
        clearUserAuthorityByUsername(user.getUsername());
        return true;
    }

    /**
     * 更新当前登录用户的密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 是否更新成功
     */
    @Override
    public boolean updatePassword(String oldPassword, String newPassword) {
        User authUser = this.getCurrentAuthUser();
        final String username = authUser.getUsername();
        User user = this.getByUsername(username);
        Assert.notNull(user, () -> new BadRequestException("更新密码错误，用户不存在"));
        boolean match = this.matchesUserPassword(username, oldPassword, user.getPassword());
        Assert.isTrue(match, () -> new BadRequestException("旧密码不正确"));
        String encodePassword = this.encodeUserPassword(username, newPassword);
        return super.lambdaUpdate().set(User::getPassword, encodePassword).eq(User::getId, user.getId()).update();
    }

    /**
     * 获取用户
     *
     * @param id 用户ID
     * @return 用户
     */
    @Override
    public User getById(String id) {
        final User user = super.getById(id);
        if (user == null) {
            throw new BadRequestException("获取用户信息失败");
        }
        final List<Role> roles = this.getRoles(user.getId());
        final Dept dept = deptService.getById(user.getDeptId());
        user.setRoles(roles);
        user.setDept(dept);
        user.setPassword(null);
        return user;
    }

    /**
     * 分页条件查询
     *
     * @param searchForm 查询表单
     * @return 数据
     */
    @Override
    public SearchResult<User> getPageList(SearchForm searchForm) {
        final SearchResult<User> searchResult = super.getPageList(searchForm);
        final List<User> users = searchResult.getItems();
        users.forEach(user -> {
            // 初始化角色信息
            final List<String> roleIds = userRoleService.lambdaQuery()
                .eq(UserRole::getUserId, user.getId())
                .list()
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
            if (roleIds.size() > 0) {
                final List<Role> roles = roleService.lambdaQuery().in(Role::getId, roleIds).list();
                user.setRoleIds(roleIds);
                user.setRoles(roles);
            }
            // 初始化部门信息
            if (deptService.exist(user.getDeptId())) {
                final Dept dept = deptService.getById(user.getDeptId());
                user.setDept(dept);
            }
            user.setPassword(null);
        });
        return searchResult;
    }

    /**
     * 根据username获取用户
     *
     * @param username 用户名
     * @return 用户
     */
    @Override
    public User getByUsername(String username) {
        // 缓存用户
        if (redisUtil.hHasKey(RedisConst.SYSTEM_USER, username)) {
            return (User) redisUtil.hget(RedisConst.SYSTEM_USER, username);
        }
        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (user != null) {
            redisUtil.hset(RedisConst.SYSTEM_USER, user.getUsername(), user);
        }
        return user;
    }

    /**
     * 获取用户角色
     *
     * @param userId 用户ID
     * @return 用户角色列表
     */
    @Override
    public List<Role> getRoles(String userId) {
        // 获取roleId
        final List<String> roleIds = userRoleService.lambdaQuery()
            .select(UserRole::getRoleId)
            .eq(UserRole::getUserId, userId)
            .list()
            .stream()
            .map(UserRole::getRoleId)
            .collect(Collectors.toList());
        List<Role> roles = new ArrayList<>();
        if (roleIds.size() > 0) {
            // 获取role
            roles = roleService.lambdaQuery().in(Role::getId, roleIds).list();
        }
        return roles;
    }

    /**
     * 获取当前用户角色
     *
     * @return 用户角色列表
     */
    @Override
    public List<Role> getRoles() {
        final User authUser = getCurrentAuthUser();
        return getRoles(authUser.getId());
    }

    /**
     * 获取用户部门
     *
     * @param userId 用户ID
     * @return 用户部门
     */
    @Override
    public Dept getDept(String userId) {
        final User user = super.getById(userId);
        final String deptId = Assert.notNull(user, () -> {
            log.error("User " + userId + " not exist in database");
            return new BadRequestException("用户不存在");
        }).getDeptId();
        return deptService.getById(deptId);
    }

    /**
     * 获取当前用户部门
     *
     * @return 用户部门
     */
    @Override
    public Dept getDept() {
        final User authUser = this.getCurrentAuthUser();
        if (authUser == null) {
            return null;
        }
        return getDept(authUser.getDeptId());
    }

    /**
     * 获取用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表字符串，用逗号分隔
     */
    @Override
    public List<String> getUserAuthority(String userId) {
        final User user = this.getById(userId);

        // 在Redis中缓存用户权限信息
        if (redisUtil.hHasKey(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername())) {
            return redisUtil.hget(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername());
        }
        // 获取角色
        final List<Role> roles = this.getRoles(userId);
        if (roles.size() == 0) {
            return CollUtil.empty(String.class);
        }
        // 获取菜单权限
        final List<Menu> menus = menuService.getUserMenuList(userId);
        // 生成security权限列表字符串
        final List<String> roleAuthorities = roles.stream()
            .map(role -> "ROLE_" + role.getCode())
            .collect(Collectors.toList());
        final List<String> menuAuthorities = menus.stream()
            .map(Menu::getPermissions)
            .filter(StringUtils::isNotBlank)
            .collect(Collectors.toList());
        roleAuthorities.addAll(menuAuthorities);
        redisUtil.hset(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername(), roleAuthorities);
        return roleAuthorities;
    }

    /**
     * 获取当前登录的用户
     *
     * @return 用户
     */
    @Override
    public User getCurrentAuthUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedException();
        }
        final String username = authentication.getName();
        final User user = this.getByUsername(username);
        if (user == null) {
            log.error("User {} not exist in database.", username);
            throw new BadRequestException("用户不存在");
        }
        final User authUser = new User();
        BeanUtil.copyProperties(user, authUser);
        authUser.setPassword(null);
        return authUser;
    }

    /**
     * 是否已认证
     *
     * @return 是否已认证
     */
    @Override
    public boolean Authenticated() {
        return getCurrentAuthUser() != null;
    }

    /**
     * 是否拥有超级管理员身份
     *
     * @return 是否拥有超级管理员身份
     */
    @Override
    public boolean hasAdministrator() {
        final User authUser = getCurrentAuthUser();
        return this.hasAdministrator(authUser.getId());
    }

    /**
     * 是否拥有超级管理员身份
     *
     * @param userId 用户ID
     * @return 是否拥有超级管理员身份
     */
    @Override
    public boolean hasAdministrator(String userId) {
        final Role administrator = roleService.getAdministrator();
        final List<String> authorities = getUserAuthority(userId);
        return authorities.contains("ROLE_" + administrator.getCode());
    }

    /**
     * 清除用户信息缓存
     *
     * @param username 用户名
     */
    @Override
    public void clearUserInfoCacheByUsername(String username) {
        // 清除用户信息缓存
        redisUtil.hdel(RedisConst.SYSTEM_USER, username);
    }

    /**
     * 清除用户授权信息
     *
     * @param username 用户名
     */
    @Override
    public void clearUserAuthorityByUsername(String username) {
        // 清除授权
        redisUtil.hdel(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, username);
        // 清除菜单列表
        redisUtil.hdel(RedisConst.SYSTEM_USER_MENU_LIST, username);
        // 清除菜单树
        redisUtil.hdel(RedisConst.SYSTEM_USER_MENU_TREE, username);
        // 清除用户信息缓存
        clearUserInfoCacheByUsername(username);
    }

    /**
     * 清除用户授权信息
     *
     * @param roleId 角色ID
     */
    @Override
    public void clearUserAuthorityByRoleId(String roleId) {
        // 获取userId
        final List<String> userIds = userRoleService.lambdaQuery()
            .select(UserRole::getUserId)
            .eq(UserRole::getRoleId, roleId)
            .list()
            .stream()
            .map(UserRole::getUserId)
            .collect(Collectors.toList());
        if (userIds.size() == 0) {
            return;
        }
        // 获取username
        final List<String> usernames = this.lambdaQuery()
            .select(User::getUsername)
            .in(User::getId, userIds)
            .list()
            .stream()
            .map(User::getUsername)
            .collect(Collectors.toList());
        // 清除用户授权信息
        usernames.forEach(this::clearUserAuthorityByUsername);
    }

    /**
     * 清除用户授权信息
     *
     * @param menuId 权限菜单ID
     */
    @Override
    public void clearUserAuthorityByMenuId(String menuId) {
        // 根据权限菜单ID获取角色ID
        final List<String> roleIds = roleMenuService.lambdaQuery()
            .select(RoleMenu::getRoleId)
            .eq(RoleMenu::getMenuId, menuId)
            .list()
            .stream()
            .map(RoleMenu::getRoleId)
            .collect(Collectors.toList());
        // 清除用户授权信息
        roleIds.forEach(this::clearUserAuthorityByRoleId);
    }

    /**
     * 用户密码加密
     *
     * @param username 用户名
     * @param password 密码
     * @return 加密密码
     */
    @Override
    public String encodeUserPassword(String username, String password) {
        return passwordEncoder.encode(username + password);
    }

    /**
     * 验证用户密码是否正确
     *
     * @param username 用户名
     * @param password 未加密密码
     * @param encodedPassword 已加密密码
     * @return 用户密码是否正确
     */
    @Override
    public boolean matchesUserPassword(String username, String password, String encodedPassword) {
        return passwordEncoder.matches(username + password, encodedPassword);
    }

    /**
     * 校验用户
     *
     * @param user 用户
     * @param isUpdate 是否是更新操作
     */
    private void validate(User user, boolean isUpdate) {
        long count;
        // 校验账号是否唯一
        if (isUpdate) {
            // 密码加密
            if (StrUtil.isNotBlank(user.getPassword())) {
                user.setPassword(encodeUserPassword(user.getUsername(), user.getPassword()));
            }
            count = super.lambdaQuery().eq(User::getUsername, user.getUsername()).ne(User::getId, user.getId()).count();
        } else {
            ValidatorUtil.validateEntity(user, AddValidGroup.class);
            // 密码加密
            user.setPassword(encodeUserPassword(user.getUsername(), user.getPassword()));
            count = super.lambdaQuery().eq(User::getUsername, user.getUsername()).count();
        }
        if (count > 0) {
            throw new BadRequestException("该用户已存在");
        }
    }

}
