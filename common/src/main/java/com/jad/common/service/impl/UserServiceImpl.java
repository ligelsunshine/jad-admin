/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import com.jad.common.function.PropertyFunc;
import com.jad.common.lang.SearchResult;
import com.jad.common.mapper.UserMapper;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import cn.hutool.core.util.StrUtil;

/**
 * 系统用户服务类
 *
 * @author cxxwl96
 * @since 2021-06-18
 */
@Service
// @DS("master")
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {

    // yaml中配置的超级管理员ID
    @Value("${jad.system.role.administrator-id}")
    private String administratorId;

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
        // 清空缓存的用户
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
        // 清空缓存的用户
        clearUserAuthorityByUsername(user.getUsername());
        return true;
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
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
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
     * 获取用户权限列表
     *
     * @param userId 用户id
     * @return 用户权限列表字符串，用逗号分隔
     */
    @Override
    public String getUserAuthority(String userId) {
        String authority;
        final User user = this.getById(userId);

        // 在Redis中缓存用户权限信息
        if (redisUtil.hHasKey(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername())) {
            authority = (String) redisUtil.hget(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername());
        } else {
            // 获取角色
            final List<Role> roles = this.getRoles(userId);
            if (roles.size() == 0) {
                return "";
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
            authority = String.join(",", roleAuthorities);

            redisUtil.hset(RedisConst.SECURITY_USER_GRANTED_AUTHORITY, user.getUsername(), authority);
        }
        return authority;
    }

    /**
     * 获取当前登录的用户
     *
     * @return 用户
     */
    @Override
    public User getCurrentAuthUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final String username = authentication.getName();
        final User user = this.getByUsername(username);
        if (user != null) {
            user.setPassword(null);
        }
        return user;
    }

    /**
     * 获取超级管理员角色
     *
     * @return 是否拥有超级管理员身份
     */
    @Override
    public Role getAdministrator() {
        if (redisUtil.hHasKey(RedisConst.SYSTEM_ROLE, RedisConst.SYSTEM_ROLE_ADMINISTRATOR)) {
            final String json = (String) redisUtil.hget(RedisConst.SYSTEM_ROLE, RedisConst.SYSTEM_ROLE_ADMINISTRATOR);
            return JSONObject.parseObject(json, Role.class);
        }
        final Role administrator = roleService.getById(administratorId);
        redisUtil.hset(RedisConst.SYSTEM_ROLE, RedisConst.SYSTEM_ROLE_ADMINISTRATOR, JSON.toJSONString(administrator));
        return administrator;
    }

    /**
     * 是否拥有超级管理员身份
     *
     * @return 是否拥有超级管理员身份
     */
    @Override
    public boolean hasAdministrator() {
        final Role administrator = getAdministrator();
        final User authUser = getCurrentAuthUser();
        final String authority = getUserAuthority(authUser.getId());
        final String[] permCode = authority.split(",");
        final Optional<String> first = Arrays.stream(permCode)
            .filter((perm) -> ("ROLE_" + administrator.getCode()).equals(perm))
            .findFirst();
        return first.isPresent();
    }

    /**
     * 是否拥有超级管理员身份
     *
     * @param userId 用户ID
     * @return 是否拥有超级管理员身份
     */
    @Override
    public boolean hasAdministrator(String userId) {
        final Role administrator = getAdministrator();
        final String authority = getUserAuthority(userId);
        final String[] permCode = authority.split(",");
        final Optional<String> first = Arrays.stream(permCode)
            .filter((perm) -> ("ROLE_" + administrator.getCode()).equals(perm))
            .findFirst();
        return first.isPresent();
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
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            count = super.lambdaQuery().eq(User::getUsername, user.getUsername()).ne(User::getId, user.getId()).count();
        } else {
            ValidatorUtil.validateEntity(user, AddValidGroup.class);
            // 密码加密
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            count = super.lambdaQuery().eq(User::getUsername, user.getUsername()).count();
        }
        if (count > 0) {
            throw new BadRequestException("该用户已存在");
        }
    }
}
