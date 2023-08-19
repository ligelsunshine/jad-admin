/*
 * Copyright (c) 2021-2021, jad (cxxwl96@sina.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jad.generator.enums;

/**
 * 前端组件
 *
 * @author cxxwl96
 * @since 2021/9/12 14:26
 */
public enum ComponentType {
    // 普通文本框
    Input,
    // 输入组
    InputGroup,
    // 密码框
    InputPassword,
    // 搜索框
    InputSearch,
    // 文本域
    InputTextArea,
    // 数字框
    InputNumber,
    // 倒计时输入框
    InputCountDown,
    // 下拉框
    Select,
    // API下拉框
    ApiSelect,
    // 树形选择器
    TreeSelect,
    // 单选方形按钮组
    RadioButtonGroup,
    // 单选圆形按钮组
    RadioGroup,
    // 复选框
    Checkbox,
    // 复选框组
    CheckboxGroup,
    // 自动匹配输入框
    AutoComplete,
    // 级联
    Cascader,
    // 日期选择器
    DatePicker,
    // 月份选择器
    MonthPicker,
    // 日期范围选择器
    RangePicker,
    // 周选择器
    WeekPicker,
    // 时间选择器
    TimePicker,
    // 开关
    Switch,
    // 密码强度校验组件
    StrengthMeter,
    // 上传组件
    Upload,
    // ICON选择器
    IconPicker,
    // 渲染器
    Render,
    // 滑动输入条
    Slider,
    // 评分
    Rate,
}
