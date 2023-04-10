<#assign title="${model.title}"/>
<#assign module="${model.module}"/>
<#assign Entity="${model.bigHump}"/>
<#assign entity="${model.smallHump}"/>
<#assign hasDateType=false/>
<#list model.fieldSchema as field>
    <#if field.type=='DATE'>
        <#assign hasDateType=true/>
    </#if>
</#list>
import { <#if !model.treeModel>BasicColumn, </#if>FormSchema } from '/@/components/Table';
import { DescItem } from '/@/components/Description';
import { formatToDateTime } from '/@/utils/dateUtil';

/**
* Entity
*/
export interface ${Entity} {
id: string;
<#if model.treeModel>
    pId?: string;
    code?: string;
    orderNo?: number;
</#if>
<#list model.fieldSchema as field>
    <#switch field.type!"">
        <#case "STRING">
        <#case "TEXT">
        <#case "DATE">
            ${field.smallHump}?: string;
            <#break>
        <#case "INT">
        <#case "FLOAT">
        <#case "DOUBLE">
        <#case "LONG">
        <#case "DECIMAL">
            ${field.smallHump}?: number;
            <#break>
        <#case "BOOLEAN">
            ${field.smallHump}?: boolean;
            <#break>
        <#case "ENUM">
            ${field.smallHump}?: ${field.bigHump};
            <#break>
        <#case "OBJECT">
            ${field.smallHump}?: any;
            <#break>
        <#default>
            ${field.smallHump}?: string;
            <#break>
    </#switch>
</#list>
<#if model.treeModel>
    children?: ${Entity}[];
</#if>
}
<#list model.fieldSchema as field>
    <#if field.type=='ENUM'>

        /**
        * ${field.title}枚举
        */
        export enum ${field.bigHump} {
        <#list field.enumVal as enum>
            ${enum.upperCaseUnderline} = ${enum_index},
        </#list>
        }

        /**
        * ${field.title}渲染
        *
        * @param ${field.smallHump} ${field.title}
        */
        export function renderOf${field.bigHump}(${field.smallHump}) {
        switch (${field.smallHump}) {
        <#list field.enumVal as enum>
            case ${field.bigHump}.${enum.upperCaseUnderline}:
            return '${enum.title}';
        </#list>
        default:
        return 'undefined';
        }
        }
    </#if>
</#list>
<#if !model.treeModel>

    /**
    * 列表显示字段
    */
    export const columns: BasicColumn[] = [
    <#list model.fieldSchema as field>
        <#list field.presents as present>
            <#if present == 'LIST'>
                {
                dataIndex: '${field.smallHump}',
                title: '${field.title}',
                <#if field.type == 'BOOLEAN'>
                    customRender: ({ record }) => (record.${field.smallHump} ? '是' : '否'),
                <#elseif field.type == 'DATE'>
                    customRender: ({ record }) => {
                    if (record.${field.smallHump}) {
                    return formatToDateTime(record.${field.smallHump});
                    } else {
                    return '';
                    }
                    },
                <#elseif field.type == 'ENUM'>
                    customRender: ({ record }) => renderOf${field.bigHump}(record.${field.smallHump}),
                </#if>
                },
                <#break>
            </#if>
        </#list>
    </#list>
    {
    dataIndex: 'createTime',
    title: '创建时间',
    width: '200px',
    customRender: ({ record }) => (record.createTime ? formatToDateTime(record.createTime) : ''),
    },
    ];

    /**
    * 查询表单
    */
    export const searchFormSchema: FormSchema[] = [
    <#list model.fieldSchema as field>
        <#list field.presents as present>
            <#if present == 'SEARCH_FORM'>
                {
                field: '${field.smallHump}',
                label: '${field.title}',
                <#switch field.component>
                    <#case 'Input'>
                        component: 'Input',
                        <#break>
                    <#case 'InputPassword'>
                        component: 'Input',
                        <#break>
                    <#case 'InputTextArea'>
                        component: 'Input',
                        <#break>
                    <#case 'InputNumber'>
                        component: 'InputNumber',
                        <#break>
                    <#case 'Select'>
                        <#if field.type == 'ENUM'>
                            component: 'Select',
                            componentProps: {
                            options: [
                            <#list field.enumVal as enum>
                                { label: '${enum.title}', value: ${field.bigHump}.${enum.upperCaseUnderline} },
                            </#list>
                            ],
                            },
                        <#else>
                            component: 'Input',
                        </#if>
                        <#break>
                    <#case 'RadioButtonGroup'>
                        <#if field.type == 'BOOLEAN'>
                            component: 'RadioButtonGroup',
                            componentProps: {
                            options: [
                            { label: '是', value: true },
                            { label: '否', value: false },
                            ],
                            },
                        <#elseif field.type == 'ENUM'>
                            component: 'RadioButtonGroup',
                            componentProps: {
                            options: [
                            <#list field.enumVal as enum>
                                { label: '${enum.title}', value: ${field.bigHump}.${enum.upperCaseUnderline} },
                            </#list>
                            ],
                            },
                        <#else>
                            component: 'Input',
                        </#if>
                        <#break>
                    <#case 'RadioGroup'>
                        <#if field.type == 'BOOLEAN'>
                            component: 'RadioGroup',
                            componentProps: {
                            options: [
                            { label: '是', value: true },
                            { label: '否', value: false },
                            ],
                            },
                        <#elseif field.type == 'ENUM'>
                            component: 'RadioGroup',
                            componentProps: {
                            options: [
                            <#list field.enumVal as enum>
                                { label: '${enum.title}', value: ${field.bigHump}.${enum.upperCaseUnderline} },
                            </#list>
                            ],
                            },
                        <#else>
                            component: 'Input',
                        </#if>
                        <#break>
                    <#case 'Checkbox'>
                        <#if field.type == 'BOOLEAN'>
                            component: 'Checkbox',
                        <#else>
                            component: 'Input',
                        </#if>
                        <#break>
                    <#case 'CheckboxGroup'>
                        <#if field.type == 'BOOLEAN'>
                            component: 'CheckboxGroup',
                        <#else>
                            component: 'Input',
                        </#if>
                        <#break>
                    <#case 'DatePicker'>
                        component: 'DatePicker',
                        <#break>
                    <#case 'MonthPicker'>
                        component: 'MonthPicker',
                        <#break>
                    <#case 'RangePicker'>
                        component: 'RangePicker',
                        <#break>
                    <#case 'WeekPicker'>
                        component: 'WeekPicker',
                        <#break>
                    <#case 'TimePicker'>
                        component: 'TimePicker',
                        <#break>
                    <#case 'Switch'>
                        component: 'Select',
                        componentProps: {
                        options: [
                        { label: '是', value: 'true' },
                        { label: '否', value: 'false' },
                        ],
                        },
                        <#break>
                    <#case 'StrengthMeter'>
                        component: 'Input',
                        <#break>
                    <#case 'Upload'>
                        component: 'Input',
                        <#break>
                    <#case 'IconPicker'>
                        component: 'IconPicker',
                        <#break>
                    <#case 'Rate'>
                        component: 'Rate',
                        <#break>
                    <#default>
                        component: 'Input',
                </#switch>
                colProps: { span: 8 },
                },
                <#break>
            </#if>
        </#list>
    </#list>
    {
    field: 'rangeTime',
    label: '创建时间',
    component: 'RangePicker',
    colProps: { span: 8 },
    },
    ];
</#if>

/**
* 添加/编辑表单
*/
export const formSchema: FormSchema[] = [
{
field: '_isUpdate',
label: '',
component: 'Switch',
show: false,
},
{
field: 'id',
label: 'ID',
component: 'Input',
show: false,
},
<#if model.treeModel>
    {
    field: 'pid',
    label: '上级${title}',
    component: 'TreeSelect',
    componentProps: {
    replaceFields: {
    title: 'id',
    key: 'id',
    value: 'id',
    },
    getPopupContainer: () => document.body,
    },
    },
    {
    field: 'code',
    label: '编码',
    component: 'Input',
    rules: [
    {
    required: false,
    pattern: /[a-zA-Z0-9\-]/,
    message: '仅数字、字母、中横线组成',
    trigger: 'blur',
    },
    ],
    helpMessage: '唯一标识码，仅数字、字母、中横线组成',
    },
</#if>
<#list model.fieldSchema as field>
    <#assign addForm=false/>
    <#assign editForm=false/>
    <#list field.presents as present>
        <#if present == 'ADD_FORM'>
            <#assign addForm=true/>
        </#if>
        <#if present == 'EDIT_FORM'>
            <#assign editForm=true/>
        </#if>
    </#list>
    <#if (addForm || editForm)>
        {
        field: '${field.smallHump}',
        label: '${field.title}',
        <#switch field.component>
            <#case 'Input'>
                component: 'Input',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
                <#break>
            <#case 'InputPassword'>
                component: 'InputPassword',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
                <#break>
            <#case 'InputTextArea'>
                component: 'InputTextArea',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
                <#break>
            <#case 'InputNumber'>
                component: 'InputNumber',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
                <#break>
            <#case 'Select'>
                <#if field.type == 'ENUM'>
                    component: 'Select',
                    <#if field.defaultVal??>
                        <#list field.enumVal as enum>
                            <#if field.defaultVal == enum.name>
                                defaultValue: ${field.bigHump}.${enum.upperCaseUnderline},
                                <#break>
                            </#if>
                        </#list>
                    </#if>
                    componentProps: {
                    options: [
                    <#list field.enumVal as enum>
                        { label: '${enum.title}', value: ${field.bigHump}.${enum.upperCaseUnderline} },
                    </#list>
                    ],
                    },
                <#else>
                    component: 'Input',
                </#if>
                <#break>
            <#case 'RadioButtonGroup'>
                <#if field.type == 'BOOLEAN'>
                    component: 'RadioButtonGroup',
                    componentProps: {
                    options: [
                    { label: '是', value: true },
                    { label: '否', value: false },
                    ],
                    },
                <#elseif field.type == 'ENUM'>
                    component: 'RadioButtonGroup',
                    <#if field.defaultVal??>
                        <#list field.enumVal as enum>
                            <#if field.defaultVal == enum.name>
                                defaultValue: ${field.bigHump}.${enum.upperCaseUnderline},
                                <#break>
                            </#if>
                        </#list>
                    </#if>
                    componentProps: {
                    options: [
                    <#list field.enumVal as enum>
                        { label: '${enum.title}', value: ${field.bigHump}.${enum.upperCaseUnderline} },
                    </#list>
                    ],
                    },
                <#else>
                    component: 'Input',
                </#if>
                <#break>
            <#case 'RadioGroup'>
                <#if field.type == 'BOOLEAN'>
                    component: 'RadioGroup',
                    componentProps: {
                    options: [
                    { label: '是', value: true },
                    { label: '否', value: false },
                    ],
                    },
                <#elseif field.type == 'ENUM'>
                    component: 'RadioGroup',
                    <#if field.defaultVal??>
                        <#list field.enumVal as enum>
                            <#if field.defaultVal == enum.name>
                                defaultValue: ${field.bigHump}.${enum.upperCaseUnderline},
                                <#break>
                            </#if>
                        </#list>
                    </#if>
                    componentProps: {
                    options: [
                    <#list field.enumVal as enum>
                        { label: '${enum.title}', value: ${field.bigHump}.${enum.upperCaseUnderline} },
                    </#list>
                    ],
                    },
                <#else>
                    component: 'Input',
                </#if>
                <#break>
            <#case 'Checkbox'>
                <#if field.type == 'BOOLEAN'>
                    component: 'Checkbox',
                <#else>
                    component: 'Input',
                </#if>
                <#break>
            <#case 'CheckboxGroup'>
                <#if field.type == 'BOOLEAN'>
                    component: 'CheckboxGroup',
                <#else>
                    component: 'Input',
                </#if>
                <#break>
            <#case 'DatePicker'>
                component: 'DatePicker',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
                <#break>
            <#case 'MonthPicker'>
                component: 'MonthPicker',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
                <#break>
            <#case 'RangePicker'>
                component: 'RangePicker',
                <#break>
            <#case 'WeekPicker'>
                component: 'WeekPicker',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
                <#break>
            <#case 'TimePicker'>
                component: 'TimePicker',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
                <#break>
            <#case 'Switch'>
                <#if field.type == 'BOOLEAN'>
                    component: 'Switch',
                    <#if field.require>
                        defaultValue: false,
                    </#if>
                <#else>
                    component: 'Input',
                </#if>
                <#break>
            <#case 'StrengthMeter'>
                component: 'StrengthMeter',
                <#break>
            <#case 'Upload'>
                component: 'Upload',
                <#break>
            <#case 'IconPicker'>
                component: 'IconPicker',
                <#break>
            <#case 'Rate'>
                component: 'Rate',
                <#break>
            <#default>
                component: 'Input',
                <#if field.defaultVal??>
                    defaultValue: '${field.defaultVal}',
                </#if>
        </#switch>
        <#if field.require>
            required: true,
        </#if>
        <#if field.rules?? && field.rules?size gt 0>
            rules: [
            <#list field.rules as rule>
                <#switch rule.type!"">
                    <#case "STRING_LEN">
                        <#if rule.len??>
                            { required: true, message: '${field.title}长度必须为${rule.len}', pattern: /^[^S]{${rule.len}}$/, trigger: 'blur' },
                        </#if>
                        <#break>
                    <#case "STRING_RANGE">
                        <#if rule.min?? && rule.max??>
                            { required: true, message: '${field.title}长度必须在${rule.min}-${rule.max}之间', pattern: /^[^S]{${rule.min},${rule.max}}$/, trigger: 'blur' },
                        </#if>
                        <#break>
                    <#case "NUMBER">
                        { required: true, message: '${field.title}不是数字', pattern: /^-?\\d+(\\.\\d+)?$/, trigger: 'blur' },
                        <#break>
                    <#case "INTEGER">
                        { required: true, message: '${field.title}不是整数', pattern: /^-?\\d+$/, trigger: 'blur' },
                        <#break>
                    <#case "FLOAT">
                        { required: true, message: '${field.title}不是小数', pattern: /^-?\\d+\\.\\d+$/, trigger: 'blur' },
                        <#break>
                    <#case "URL">
                        { required: true, message: '${field.title}不是URL', pattern: /^[a-zA-z]+:\/\/[\S]*$/, trigger: 'blur' },
                        <#break>
                    <#case "EMAIL">
                        { required: true, message: '${field.title}邮箱格式不对', pattern: /^(\\w+@(\\w+\\.)+(\\w+))?$/, trigger: 'blur' },
                        <#break>
                    <#case "PHONE">
                        { required: true, message: '${field.title}手机格式不对', pattern: /^(1[345789]\\d{9})?$/, trigger: 'blur' },
                        <#break>
                    <#case "REGEXP">
                        { required: true, message: '${rule.message}', pattern: /${rule.pattern?replace('\\','\\\\')}/, trigger: 'blur' },
                        <#break>
                </#switch>
            </#list>
            ],
        </#if>
        <#if addForm && !editForm>
            show: ({ values }) => !values._isUpdate,
        </#if>
        <#if !addForm && editForm>
            show: ({ values }) => values._isUpdate,
        </#if>
        },
    </#if>
</#list>
<#if model.treeModel>
    {
    field: 'orderNo',
    label: '排序',
    component: 'InputNumber',
    defaultValue: 0,
    helpMessage: '默认排序为升序',
    },
</#if>
];

/**
* 详情
*/
export const descSchema: DescItem[] = [
<#list model.fieldSchema as field>
    <#list field.presents as present>
        <#if present == 'DETAIL'>
            {
            field: '${field.smallHump}',
            label: '${field.title}',
            <#if field.type == 'BOOLEAN'>
                render: (val) => (eval(val) ? '是' : '否'),
            <#elseif field.type == 'DATE'>
                render: (val) => (val ? formatToDateTime(val) : ''),
            <#elseif field.type == 'ENUM'>
                render: (val) => renderOf${field.bigHump}(val),
            </#if>
            },
            <#break>
        </#if>
    </#list>
</#list>
<#if model.treeModel>
    {
    field: 'code',
    label: '编码',
    },
    {
    field: 'orderNo',
    label: '排序',
    },
</#if>
{
field: 'createTime',
label: '创建时间',
render: (val) => (val ? formatToDateTime(val) : ''),
},
{
field: 'updateTime',
label: '修改时间',
render: (val) => (val ? formatToDateTime(val) : ''),
},
{
field: 'remark',
label: '备注',
},
];
