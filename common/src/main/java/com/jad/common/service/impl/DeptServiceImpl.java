package com.jad.common.service.impl;

import com.jad.common.base.service.impl.TreeServiceImpl;
import com.jad.common.entity.Dept;
import com.jad.common.exception.BadRequestException;
import com.jad.common.lang.Result;
import com.jad.common.mapper.DeptMapper;
import com.jad.common.service.DeptService;

import org.springframework.stereotype.Service;

import cn.hutool.core.util.StrUtil;

/**
 * 部门服务实现类
 *
 * @author cxxwl96
 * @since 2021/8/21 14:05
 */
@Service
public class DeptServiceImpl extends TreeServiceImpl<DeptMapper, Dept> implements DeptService {
    /**
     * 添加部门
     *
     * @param dept 部门
     * @return 是否添加成功
     */
    @Override
    public boolean save(Dept dept) {
        validate(dept);
        return super.save(dept);
    }

    /**
     * 修改部门
     *
     * @param dept 部门
     * @return 是否修改成功
     */
    @Override
    public boolean updateById(Dept dept) {
        validate(dept);
        return super.updateById(dept);
    }

    private void validate(Dept dept) {
        // 若部门编码存在值，且唯一，则校验部门编码是否满足：仅字母、数字和中横线组成
        if (StrUtil.isNotBlank(dept.getCode())) {
            // 是否唯一
            final long count = super.lambdaQuery()
                .eq(Dept::getCode, dept.getCode())
                .ne(Dept::getId, dept.getId())
                .count();
            if (count > 0) {
                throw new BadRequestException(Result.failed("该部门编码已存在"));
            }
            // 是否满足：仅字母、数字和中横线组成
            String pattern = "[a-zA-Z0-9\\-]+";
            if (!dept.getCode().matches(pattern)) {
                throw new BadRequestException(Result.failed("仅数字、字母、中横线组成"));
            }
        }
    }
}
