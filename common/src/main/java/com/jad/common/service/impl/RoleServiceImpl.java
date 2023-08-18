/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jad.common.base.form.OrderItem;
import com.jad.common.base.form.SearchForm;
import com.jad.common.base.form.WhereItem;
import com.jad.common.base.service.impl.BaseServiceImpl;
import com.jad.common.constant.RedisConst;
import com.jad.common.entity.Role;
import com.jad.common.enums.Condition;
import com.jad.common.enums.Status;
import com.jad.common.exception.BadRequestException;
import com.jad.common.lang.Result;
import com.jad.common.lang.SearchResult;
import com.jad.common.mapper.RoleMapper;
import com.jad.common.service.RoleService;
import com.jad.common.service.UserService;
import com.jad.common.utils.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import cn.hutool.core.collection.CollUtil;

/**
 * 角色服务实现类
 *
 * @author cxxwl96
 * @since 2021/8/22 14:34
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {
    // yaml中配置的超级管理员ID
    @Value("${jad.system.role.administrator-id}")
    private String administratorId;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 添加角色
     * - 添加的角色等级不能高于当前用户角色的最高级别
     *
     * @param role 角色
     * @return 是否添加成功
     */
    @Override
    public boolean save(Role role) {
        if (checkRoleLevel(role)) {
            return super.save(role);
        }
        throw new BadRequestException(Result.failed("您不能添加角色等级大于您拥有的最大角色等级"));
    }

    /**
     * 删除角色
     * - 删除的角色等级不能高于当前用户角色的最高级别
     *
     * @param id 角色ID
     * @return 是否删除成功
     */
    @Override
    public boolean removeById(String id) {
        if (checkRoleLevel(super.getById(id))) {
            return super.removeById(id);
        }
        throw new BadRequestException(Result.failed("您不能删除角色等级大于您拥有的最大角色等级"));
    }

    /**
     * 修改角色
     * - 修改的角色等级不能高于当前用户角色的最高级别
     *
     * @param role 角色
     * @return 是否修改成功
     */
    @Override
    public boolean updateById(Role role) {
        if (checkRoleLevel(role)) {
            return super.updateById(role);
        }
        throw new BadRequestException(Result.failed("您不能修改角色等级大于您拥有的最大角色等级"));
    }

    /**
     * 获取角色列表
     * - 角色级别降序
     * - 查询角色等级比当前用户角色最高级别低的角色
     *
     * @return 角色列表
     */
    @Override
    public List<Role> getList() {
        if (userService.hasAdministrator()) {
            return super.lambdaQuery().orderByDesc(Role::getLevel).list();
        } else {
            // 只能查询角色等级比当前用户角色最高级别低的角色
            // 获取当前用户的角色中level最大的角色
            final Role maxLevelRole = getMaxLevelRole();
            if (maxLevelRole != null) {
                // 添加level <= maxLevel, order by level desc条件
                return super.lambdaQuery()
                    .lt(Role::getLevel, maxLevelRole.getLevel())
                    .orderByDesc(Role::getLevel)
                    .list();
            } else {
                throw new BadRequestException("您的账号还未分配角色");
            }
        }
    }

    /**
     * 分页查询角色
     * - 角色级别降序
     * - 查询角色等级比当前用户角色最高级别低的角色
     *
     * @param searchForm 查询表单
     * @return 角色列表
     */
    @Override
    public SearchResult<Role> getPageList(SearchForm searchForm) {
        // 添加角色级别降序
        searchForm.addOrderItem(new OrderItem().orderItem(Role::getLevel, false));
        // 是否是超级管理员
        if (userService.hasAdministrator()) {
            return super.getPageList(searchForm);
        } else {
            // 只能查询角色等级比当前用户角色最高级别低的角色
            // 获取当前用户的角色中level最大的角色
            final Role maxLevelRole = getMaxLevelRole();
            if (maxLevelRole != null) {
                // 添加level <= maxLevel 条件
                searchForm.addWhereItem(
                    new WhereItem().whereItem(Role::getLevel, Condition.LT, maxLevelRole.getLevel()));
                return super.getPageList(searchForm);
            } else {
                throw new BadRequestException("您的账号还未分配角色");
            }
        }
    }

    /**
     * 修改默认角色
     * - 角色列表中只能存在一个默认角色
     *
     * @param id 角色ID
     * @return 是否修改成功
     */
    @Override
    @Transactional
    public boolean updateDefaultRole(String id) {
        if (checkRoleLevel(super.getById(id))) {
            // 先设置所有角色为非默认角色，为避免全表更新，添加条件：只修改默认角色为非默认角色
            boolean updated = super.lambdaUpdate()
                .set(Role::getDefaultRole, false)
                .eq(Role::getDefaultRole, true)
                .update();
            if (!updated) {
                throw new BadRequestException("重置角色信息失败");
            }
            // 再设置该角色为默认角色
            updated = super.lambdaUpdate().set(Role::getDefaultRole, true).eq(Role::getId, id).update();
            if (!updated) {
                throw new BadRequestException("修改默认角色失败");
            }
            // 删除Redis存放的默认角色
            redisUtil.hdel(RedisConst.SYSTEM_ROLE, RedisConst.SYSTEM_ROLE_DEFAULT_ROLE);
            return true;
        }
        throw new BadRequestException(Result.failed("您不能修改角色等级大于您拥有的最大角色等级"));
    }

    /**
     * 修改状态
     * - 修改的角色等级不能高于当前用户角色的最高级别
     *
     * @param id 角色ID
     * @param status 状态
     * @return 是否修改成功
     */
    @Override
    public boolean updateStatus(String id, Status status) {
        if (checkRoleLevel(super.getById(id))) {
            return super.lambdaUpdate().set(Role::getStatus, status).eq(Role::getId, id).update();
        }
        throw new BadRequestException(Result.failed("您不能修改角色等级大于您拥有的最大角色等级"));
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
        final Role administrator = this.getById(administratorId);
        redisUtil.hset(RedisConst.SYSTEM_ROLE, RedisConst.SYSTEM_ROLE_ADMINISTRATOR, JSON.toJSONString(administrator));
        return administrator;
    }

    /**
     * 获取默认角色
     *
     * @return 默认角色
     */
    @Override
    public Role getDefaultRole() {
        if (redisUtil.hHasKey(RedisConst.SYSTEM_ROLE, RedisConst.SYSTEM_ROLE_DEFAULT_ROLE)) {
            final String json = (String) redisUtil.hget(RedisConst.SYSTEM_ROLE, RedisConst.SYSTEM_ROLE_DEFAULT_ROLE);
            return JSONObject.parseObject(json, Role.class);
        }
        // 获取状态为Enable的默认角色
        List<Role> roles = this.lambdaQuery().eq(Role::getDefaultRole, true).eq(Role::getStatus, Status.ENABLE).list();
        if (CollUtil.isEmpty(roles)) {
            throw new BadRequestException("默认角色角色不存在");
        }
        if (roles.size() > 1) {
            throw new BadRequestException("存在多个默认角色");
        }
        Role role = roles.get(0);
        redisUtil.hset(RedisConst.SYSTEM_ROLE, RedisConst.SYSTEM_ROLE_DEFAULT_ROLE, JSON.toJSONString(role));
        return role;
    }

    /**
     * 检查当前用户的角色等级是否支持操作角色
     *
     * @param role 角色
     * @return 是否支持
     */
    private boolean checkRoleLevel(Role role) {
        if (role == null) {
            return false;
        }
        // 是否是超级管理员
        if (userService.hasAdministrator()) {
            return true;
        } else {
            // 获取当前用户的角色中level最大的角色
            final Role maxLevelRole = getMaxLevelRole();
            return maxLevelRole != null && role.getLevel() <= maxLevelRole.getLevel();
        }
    }

    /**
     * 获取当前用户的角色中level最大的角色
     *
     * @return level最大的角色
     */
    private Role getMaxLevelRole() {
        final List<Role> roles = userService.getRoles();
        final Optional<Role> optional = roles.stream().max(Comparator.comparing(Role::getLevel));
        return optional.orElse(null);
    }
}
