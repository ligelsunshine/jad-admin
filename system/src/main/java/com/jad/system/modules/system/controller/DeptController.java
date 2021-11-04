package com.jad.system.modules.system.controller;

import com.jad.common.base.controller.BaseController;
import com.jad.common.entity.Dept;
import com.jad.common.lang.Result;
import com.jad.common.service.DeptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 系统管理 - 部门相关接口
 *
 * @author cxxwl96
 * @since 2021/8/21 12:36
 */
@Api(tags = "系统管理 - 部门相关接口")
@RestController
@RequestMapping("/sys/dept")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    @ApiOperation("添加部门")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('sys:dept:save')")
    public Result save(@RequestBody @Valid Dept dept) {
        if (deptService.save(dept)) {
            return Result.success("添加成功");
        }
        return Result.failed("添加失败");
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:dept:delete')")
    public Result delete(@PathVariable String id, boolean includeSelf) {
        if (deptService.removeTree(id, includeSelf)) {
            return Result.success("删除成功");
        }
        return Result.failed("删除失败");
    }

    @ApiOperation("修改部门")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('sys:dept:update')")
    public Result update(@RequestBody @Valid Dept dept) {
        if (deptService.updateById(dept)) {
            return Result.success("修改成功");
        }
        return Result.failed("修改失败");
    }

    @ApiOperation("获取部门")
    @GetMapping("/get/{id}")
    @PreAuthorize("@auth.hasAuthority('sys:dept:get')")
    public Result get(@PathVariable String id) {
        final Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @ApiOperation("获取部门树")
    @GetMapping("/getDeptTree")
    @PreAuthorize("@auth.hasAuthority('sys:dept:getDeptTree')")
    public Result getDeptTree() {
        List<Dept> deptTree = deptService.getRootTree();
        return Result.success(deptTree);
    }
}
