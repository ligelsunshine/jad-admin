<#assign title="${model.title}"/>
<#assign module="${model.module}"/>
<#assign Entity="${model.bigHump}"/>
<#assign entity="${model.smallHump}"/>
<#assign Service="${model.bigHump}Service"/>
<#assign service="${model.smallHump}Service"/>
<#assign Controller="${model.bigHump}Controller"/>
<#assign authPrefix="${model.namespaceSmallHump}:${entity}"/>
<#assign hasValid=false/>
<#list model.fieldSchema as field>
    <#if field.require?? || field.rules?size gt 0>
        <#assign hasValid=true/>
        <#break>
    </#if>
</#list>
<#include "../common/copyright.ftl">

package ${package};

import com.jad.common.base.controller.BaseController;
import com.jad.common.base.form.SearchForm;
import com.jad.common.lang.IDs;
import com.jad.common.lang.Result;
import com.jad.${module}.entity.${Entity};
import com.jad.${module}.service.${Service};

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

<#if hasValid>
import javax.validation.Valid;

</#if>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * ${title}相关接口
 *
 * @author ${author}
 * @since ${dateTime}
 */
@Api(tags = "${title}相关接口")
@RestController
@RequestMapping("<#if model.namespace?trim?length gt 0>/${model.namespaceSmallHump}</#if>/${entity}")
public class ${Controller} extends BaseController {
    @Autowired
    private ${Service} ${service};

    @ApiOperation("添加${title}")
    @PostMapping("/save")
    @PreAuthorize("@auth.hasAuthority('${authPrefix}:save')")
    public Result save(@RequestBody @Valid ${Entity} ${entity}) {
        if (!${service}.save(${entity})) {
            return Result.failed("添加失败");
        }
        return Result.success("添加成功");
    }

    @ApiOperation("删除${title}")
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("@auth.hasAuthority('${authPrefix}:delete')")
    public Result delete(@PathVariable String id) {
        if (!${service}.removeById(id)) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("删除多个${title}")
    @DeleteMapping("/deleteArr")
    @PreAuthorize("@auth.hasAuthority('${authPrefix}:deleteArr')")
    public Result deleteArr(@RequestBody @Valid IDs ids) {
        if (!${service}.removeByIds(ids.getIds())) {
            return Result.failed("删除失败");
        }
        return Result.success("删除成功");
    }

    @ApiOperation("修改${title}")
    @PutMapping("/update")
    @PreAuthorize("@auth.hasAuthority('${authPrefix}:update')")
    public Result update(@RequestBody @Valid ${Entity} ${entity}) {
        if (!${service}.updateById(${entity})) {
            return Result.failed("修改失败");
        }
        return Result.success("修改成功");
    }

    @ApiOperation("获取单个${title}")
    @GetMapping("/get/{id}")
    @PreAuthorize("@auth.hasAuthority('${authPrefix}:get')")
    public Result get(@PathVariable String id) {
        final ${Entity} ${entity} = ${service}.getById(id);
        if (${entity} == null){
            return Result.failed("获取数据失败");
        }
        return Result.success(${entity});
    }

    @ApiOperation("分页获取${title}")
    @PostMapping("/get/page")
    @PreAuthorize("@auth.hasAuthority('${authPrefix}:get:page')")
    public Result getPageList(@RequestBody SearchForm searchForm) {
        return Result.success("查询成功", ${service}.getPageList(searchForm));
    }
}