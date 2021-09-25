<#assign notBlank=false/>
<#assign notNull=false/>
<#assign localDateTime=false/>
<#assign localDateTimeUtil=false/>
<#assign hasEnum=false/>
<#assign bigDecimal=false/>
<#assign pattern=false/>
<#list model.fieldSchema as field>
    <#if field.require>
        <#if field.type=="STRING">
            <#assign notBlank=true/>
        <#else>
            <#assign notNull=true/>
        </#if>
    </#if>
    <#if field.type=="DATE">
        <#assign localDateTime=true/>
        <#if field.require && field.defaultVal??>
            <#assign localDateTimeUtil=true/>
        </#if>
    </#if>
    <#if field.type=="ENUM">
        <#assign hasEnum=true/>
    </#if>
    <#if field.type=="DECIMAL">
        <#assign bigDecimal=true/>
    </#if>
    <#if field.rules?? && field.rules?size gt 0>
        <#assign pattern=true/>
    </#if>
</#list>
<#include "../common/copyright.ftl">

package ${package};

<#if model.logic>
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
</#if>
import com.baomidou.mybatisplus.annotation.TableName;
<#if model.treeModel>
import com.jad.common.base.entity.TreeNode;
<#else>
import com.jad.common.base.entity.BaseEntity;
</#if>
<#if hasEnum>
    <#list model.fieldSchema as field>
        <#if field.type=="ENUM">
import com.jad.${model.module}.enums.${field.bigHump};
        </#if>
    </#list>
</#if>

import java.io.Serializable;
<#if bigDecimal>
import java.math.BigDecimal;
</#if>
<#if localDateTime>
import java.time.LocalDateTime;
</#if>
<#if notBlank || notNull || pattern>

</#if>
<#if notBlank>
import javax.validation.constraints.NotBlank;
</#if>
<#if notNull>
import javax.validation.constraints.NotNull;
</#if>
<#if pattern>
import javax.validation.constraints.Pattern;
</#if>

<#if localDateTimeUtil>
import cn.hutool.core.date.LocalDateTimeUtil;
</#if>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * ${model.title}实体
 *
 * @author ${author}
 * @since ${dateTime}
 */
@ApiModel("${model.title}实体")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
<#if model.namespace?trim?length == 0>
@TableName("${model.lowerCaseUnderline}")
<#elseif model.namespace?trim?length gt 0>
@TableName("${model.namespaceLowerCaseUnderline}_${model.lowerCaseUnderline}")
</#if>
<#if model.treeModel==true>
public class ${model.bigHump} extends TreeNode<${model.bigHump}> implements Serializable {
<#else>
public class ${model.bigHump} extends BaseEntity implements Serializable {
</#if>

    private static final long serialVersionUID = 1L;
    <#list model.fieldSchema as field>

    @ApiModelProperty(value = "${field.title}")
        <#if field.require>
            <#if field.type?? && field.type == "STRING">
    @NotBlank(message = "${field.title}不能为空")
            <#else>
    @NotNull(message = "${field.title}不能为空")
            </#if>
        </#if>
        <#list field.rules as rule>
            <#switch rule.type!"">
                <#case "STRING_LEN">
                    <#if rule.len??>
    @Pattern(message = "${field.title}长度必须为${rule.len}", regexp = "^[^S]{${rule.len}}$")
                    </#if>
                    <#break>
                <#case "STRING_RANGE">
                    <#if rule.min?? && rule.max??>
    @Pattern(message = "${field.title}长度必须在${rule.min}-${rule.max}之间", regexp = "^[^S]{${rule.min},${rule.max}}$")
                    </#if>
                    <#break>
                <#case "NUMBER">
    @Pattern(message = "${field.title}不是数字", regexp = "^-?\\d+(\\.\\d+)?$")
                    <#break>
                <#case "INTEGER">
    @Pattern(message = "${field.title}不是整数", regexp = "^-?\\d+$")
                    <#break>
                <#case "FLOAT">
    @Pattern(message = "${field.title}不是小数", regexp = "^-?\\d+\\.\\d+$")
                    <#break>
                <#case "URL">
    @Pattern(message = "${field.title}不是URL", regexp = "^[a-zA-z]+://[^S]*$")
                    <#break>
                <#case "EMAIL">
    @Pattern(message = "${field.title}邮箱格式不对", regexp = "^(\\w+@(\\w+\\.)+(\\w+))?$")
                    <#break>
                <#case "PHONE">
    @Pattern(message = "${field.title}手机格式不对", regexp = "^(1[345789]\\d{9})?$")
                    <#break>
                <#case "REGEXP">
    @Pattern(message = "${rule.message}", regexp = "${rule.pattern?replace('\\','\\\\')}")
                    <#break>
            </#switch>
        </#list>
        <#switch field.type!"">
            <#case "STRING">
            <#case "TEXT">
    private String ${field.smallHump}<#if (field.defaultVal)?? && field.defaultVal?trim?length gt 0> = "${field.defaultVal}"</#if>;
                <#break>
            <#case "INT">
    private <#if field.require>Integer<#else>int</#if> ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}</#if>;
                <#break>
            <#case "FLOAT">
    private <#if field.require>Float<#else>float</#if> ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}f</#if>;
                <#break>
            <#case "DOUBLE">
    private <#if field.require>Double<#else>double</#if> ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}</#if>;
                <#break>
            <#case "LONG">
    private <#if field.require>Long<#else>long</#if> ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}</#if>;
                <#break>
            <#case "DECIMAL">
    private BigDecimal ${field.smallHump}<#if (field.defaultVal)??> = BigDecimal.valueOf(${field.defaultVal})</#if>;
                <#break>
            <#case "BOOLEAN">
    private <#if field.require>Boolean<#else>boolean</#if> ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal?string("true","false")}</#if>;
                <#break>
            <#case "DATE">
    private LocalDateTime ${field.smallHump}<#if (field.defaultVal)??> = LocalDateTimeUtil.parse("${field.defaultVal}")</#if>;
                <#break>
            <#case "ENUM">
                <#if field.defaultVal??>
                    <#list field.enumVal as enum>
                        <#if field.defaultVal == enum.name>
    private ${field.bigHump} ${field.smallHump} = ${field.bigHump}.${enum.upperCaseUnderline};
                            <#break>
                        </#if>
                    </#list>
                <#else>
    private ${field.bigHump} ${field.smallHump};
                </#if>
                <#break>
            <#case "OBJECT">
    private Object ${field.smallHump};
                <#break>
            <#default>
    private String ${field.smallHump}<#if (field.defaultVal)??> = "${field.defaultVal}"</#if>;
                <#break>
        </#switch>
    </#list>

    <#if model.logic>
    @ApiModelProperty(value = "是否启用【未启用：0, 已启用：1（默认）】")
    @TableField(value = "enable", updateStrategy = FieldStrategy.NOT_NULL, fill = FieldFill.INSERT)
    @TableLogic(value = "1", delval = "0")
    private Boolean enable;
    </#if>
}
