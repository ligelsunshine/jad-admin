/*
 * Copyright (c) 2021-2023, jad (cxxwl96@sina.com).
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

package com.jad.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 组件
 * - Input:Input
 * - IdInput:IdInput
 * - InputGroup:InputGroup
 * - InputPassword:InputPassword
 * - InputSearch:InputSearch
 * - InputTextArea:InputTextArea
 * - InputNumber:InputNumber
 * - InputCountDown:InputCountDown
 * - Select:Select
 * - ApiSelect:ApiSelect
 * - TreeSelect:TreeSelect
 * - RadioButtonGroup:RadioButtonGroup
 * - RadioGroup:RadioGroup
 * - Checkbox:Checkbox
 * - CheckboxGroup:CheckboxGroup
 * - AutoComplete:AutoComplete
 * - Cascader:Cascader
 * - DatePicker:DatePicker
 * - MonthPicker:MonthPicker
 * - RangePicker:RangePicker
 * - WeekPicker:WeekPicker
 * - TimePicker:TimePicker
 * - Switch:Switch
 * - StrengthMeter:StrengthMeter
 * - Upload:Upload
 * - IconPicker:IconPicker
 * - Render:Render
 * - Slider:Slider
 * - Rate:Rate
 *
 * @author cxxwl96
 * @since 2023/08/28 22:24
 */
public enum Component {
    INPUT(0),
    ID_INPUT(1),
    INPUT_GROUP(2),
    INPUT_PASSWORD(3),
    INPUT_SEARCH(4),
    INPUT_TEXT_AREA(5),
    INPUT_NUMBER(6),
    INPUT_COUNT_DOWN(7),
    SELECT(8),
    API_SELECT(9),
    TREE_SELECT(10),
    RADIO_BUTTON_GROUP(11),
    RADIO_GROUP(12),
    CHECKBOX(13),
    CHECKBOX_GROUP(14),
    AUTO_COMPLETE(15),
    CASCADER(16),
    DATE_PICKER(17),
    MONTH_PICKER(18),
    RANGE_PICKER(19),
    WEEK_PICKER(20),
    TIME_PICKER(21),
    SWITCH(22),
    STRENGTH_METER(23),
    UPLOAD(24),
    ICON_PICKER(25),
    RENDER(26),
    SLIDER(27),
    RATE(28);

    @EnumValue
    @JsonValue
    private final int index;

    Component(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
