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

package com.jad.common.utils;

import java.util.Locale;

import cn.hutool.core.util.StrUtil;

/**
 * HexUtil
 *
 * @author cxxwl96
 * @since 2021/12/18 23:26
 */
public class HexUtil {
    public static String prettify(String hexStr) {
        if (StrUtil.isBlank(hexStr)) {
            return "";
        }
        String text = hexStr.toUpperCase(Locale.ROOT).replaceAll("\\s", "");
        String regex = "[0-9a-zA-Z]{2}";
        if (text.startsWith("0X")) {
            text = text.substring(2);
        }
        if (text.length() % 2 == 1) {
            text = "0" + text;
        }
        return text.replaceAll(regex, "$0 ").trim();
    }

}
