<#if model.namespace?trim?length == 0>
CREATE TABLE `${model.lowerCaseUnderline}` (
<#elseif model.namespace?trim?length gt 0>
CREATE TABLE `${model.namespaceLowerCaseUnderline}_${model.lowerCaseUnderline}` (
</#if>
    `id` varchar(64) NOT NULL COMMENT '主键',
<#if model.treeModel>
    `p_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '父级菜单ID',
    `order_no` int DEFAULT NULL COMMENT '排序',
    `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '编码',
</#if>
<#if model.fieldSchema?size gt 0>
    <#list model.fieldSchema as field>
        <#assign fieldName="${field.lowerCaseUnderline}"/>
        <#switch field.type>
            <#case "STRING">
                <#assign strLen = 255/>
                <#if field.rules?? && field.rules?size gt 0>
                    <#list field.rules as rule>
                        <#if rule.type == "STRING" && rule.max?? && rule.max gt 255>
                            <#assign strLen = rule.max/>
                        <#elseif rule.type == "STRING" && rule.len?? && rule.len gt 255>
                            <#assign strLen = rule.len/>
                        </#if>
                    </#list>
                </#if>
    `${fieldName}` varchar(${strLen}) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '${field.title}',
                <#break>
            <#case "TEXT">
    `${fieldName}` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '${field.title}',
                <#break>
            <#case "INT">
    `${fieldName}` int DEFAULT NULL COMMENT '${field.title}',
                <#break>
            <#case "FLOAT">
    `${fieldName}` float DEFAULT NULL COMMENT '${field.title}',
                <#break>
            <#case "DOUBLE">
    `${fieldName}` double DEFAULT NULL COMMENT '${field.title}',
                <#break>
            <#case "LONG">
    `${fieldName}` bigint DEFAULT NULL COMMENT '${field.title}',
                <#break>
            <#case "DECIMAL">
    `${fieldName}` decimal(10,0) DEFAULT NULL COMMENT '${field.title}',
                <#break>
            <#case "BOOLEAN">
    `${fieldName}` bit(1) DEFAULT NULL COMMENT '${field.title}',
                <#break>
            <#case "DATE">
    `${fieldName}` datetime DEFAULT NULL COMMENT '${field.title}',
                <#break>
            <#case "ENUM">
                <#assign enumComent = ""/>
                <#if field.enumVal?size == 1>
                    <#assign enumComent = "【${field.enumVal[0].title}:0】"/>
                <#elseif field.enumVal?size gt 1>
                    <#assign enumComent = "【"/>
                    <#list field.enumVal as enum>
                        <#if enum_has_next>
                            <#assign enumComent += "${enum.title}:${enum_index}, "/>
                        <#else>
                            <#assign enumComent += "${enum.title}:${enum_index}"/>
                        </#if>
                    </#list>
                    <#assign enumComent += "】"/>
                </#if>
    `${fieldName}` tinyint DEFAULT '0' COMMENT '${field.title}${enumComent}',
                <#break>
        </#switch>
    </#list>
</#if>
    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人',
    `update_time` datetime DEFAULT NULL COMMENT '更新时间',
    `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '更新人',
<#if model.logic>
    `enable` bit(1) DEFAULT NULL COMMENT '是否启用【未启用：0,已启用：1】',
</#if>
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;