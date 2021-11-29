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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MatchUtil
 *
 * @author cxxwl96
 * @since 2021/11/29 19:12
 */
public class MatchUtil {

    /**
     * 获取匹配到的字符串集合
     *
     * @param input 文本
     * @param regex 正则
     * @return 匹配到的字符串集合
     */
    public static List<String> getMatchList(final String input, final String regex) {
        return getMatchList(input, regex, false);
    }

    /**
     * 获取匹配到的字符串集合
     *
     * @param input 文本
     * @param regex 正则
     * @param ignoreCase 是否忽略大小写
     * @return 匹配到的字符串集合
     */
    public static List<String> getMatchList(final String input, final String regex, final boolean ignoreCase) {
        ArrayList<String> result = new ArrayList<>();
        Pattern pattern;
        if (ignoreCase) {
            pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        } else {
            pattern = Pattern.compile(regex);
        }
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            result.add(matcher.group());
        }
        result.trimToSize();
        return result;
    }
}
