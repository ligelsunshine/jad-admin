<#include "../common/copyright.ftl">

package ${package};

<#list imports as pkg>
import ${pkg};
</#list>

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
@TableName("${model.name}")
<#elseif model.namespace?trim?length gt 0>
@TableName("${model.namespace}_${model.name}")
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
                <#case "STRING">
                    <#if rule.len??>
    @Pattern(message = "${field.title}长度必须为${rule.len}", regexp = "^[^S]{${rule.len}}$")
                    <#elseif rule.min?? && rule.max??>
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
    private String ${field.smallHump}<#if (field.defaultVal)??> = "${field.defaultVal}"</#if>;
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
