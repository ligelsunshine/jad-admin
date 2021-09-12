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
<#if (model.namespace!"") == "">
@TableName("${model.name}")
<#elseif (model.namespace!"") != "">
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
        <#switch field.type!"">
            <#case "STRING">
                <#if field.require>
    @NotBlank(message = "${field.title}不能为空")
                </#if>
    private String ${field.smallHump}<#if (field.defaultVal)??> = "${field.defaultVal}"</#if>;
                <#break>
            <#case "INT">
                <#if field.require>
    @NotNull(message = "${field.title}不能为空")
    private Integer ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}</#if>;
                <#else>
    private int ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}</#if>;
                </#if>
                <#break>
            <#case "FLOAT">
                <#if field.require>
    @NotNull(message = "${field.title}不能为空")
    private Float ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}f</#if>;
                <#else>
    private float ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}f</#if>;
                </#if>
                <#break>
            <#case "DOUBLE">
                <#if field.require>
    @NotNull(message = "${field.title}不能为空")
    private Double ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}</#if>;
                <#else>
    private double ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal}</#if>;
                </#if>
                <#break>
            <#case "BOOLEAN">
                <#if field.require>
    @NotNull(message = "${field.title}不能为空")
    private Boolean ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal?string("true","false")}</#if>;
                <#else>
    private boolean ${field.smallHump}<#if (field.defaultVal)??> = ${field.defaultVal?string("true","false")}</#if>;
                </#if>
                <#break>
            <#case "DATE">
                <#if field.require>
    @NotNull(message = "${field.title}不能为空")
                </#if>
    private LocalDateTime ${field.smallHump}<#if (field.defaultVal)??> = LocalDateTimeUtil.parse("${field.defaultVal}")</#if>;
                <#break>
            <#case "ENUM">
                <#if field.require>
    @NotNull(message = "请选择${field.title}")
                </#if>
    private ${field.bigHump} ${field.smallHump};
                <#break>
            <#case "OBJECT">
                <#if field.require>
    @NotNull(message = "${field.title}不能为空")
                </#if>
    private Object ${field.smallHump};
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
