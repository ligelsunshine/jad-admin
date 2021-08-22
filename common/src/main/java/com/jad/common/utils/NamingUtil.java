package com.jad.common.utils;

import com.jad.common.enums.ConvertType;

import cn.hutool.core.util.StrUtil;

/**
 * 命名规则约束 工具类
 *
 * @author cxxwl96
 * @since 2021/8/22 21:45
 */
public class NamingUtil {

    /**
     * 转大驼峰
     *
     * @param name 名称
     * @return 结果
     */
    public static String toBigHump(String name) {
        return convert(name, ConvertType.BigHump, "");
    }

    /**
     * 转小驼峰
     *
     * @param name 名称
     * @return 结果
     */
    public static String toSmallHump(String name) {
        return convert(name, ConvertType.SmallHump, "");
    }

    /**
     * 转大写下划线
     *
     * @param name 名称
     * @return 结果
     */
    public static String toUpperCaseUnderline(String name) {
        return convert(name, ConvertType.UpperCaseUnderline, "_");
    }

    /**
     * 转小写下划线
     *
     * @param name 名称
     * @return 结果
     */
    public static String toLowerCaseUnderline(String name) {
        return convert(name, ConvertType.LowerCaseUnderline, "_");
    }

    /**
     * 转大写短横线
     *
     * @param name 名称
     * @return 结果
     */
    public static String toUpperCaseDash(String name) {
        return convert(name, ConvertType.UpperCaseDash, "-");
    }

    /**
     * 转小写短横线
     *
     * @param name 名称
     * @return 结果
     */
    public static String toLowerCaseDash(String name) {
        return convert(name, ConvertType.LowerCaseDash, "-");
    }

    /**
     * 命名转换
     *
     * @param name 名称
     * @param type 转换类型
     * @param character 连接字符
     * @return 结构
     */
    private static String convert(String name, ConvertType type, String character) {
        if (StrUtil.isBlank(name)) {
            return null;
        }
        // 按规则分离
        String[] arr = split(name);
        // 新名称
        StringBuilder newName = new StringBuilder();
        // 1、生成第一组字符串
        if (type == ConvertType.BigHump) {
            // 首写字母大写
            newName.append(capitalizeTheFirstLetter(arr[0]));
        } else if (type == ConvertType.UpperCaseUnderline || type == ConvertType.UpperCaseDash) {
            // 转大写
            newName.append(arr[0].toUpperCase());
        } else if (type == ConvertType.SmallHump || type == ConvertType.LowerCaseUnderline
            || type == ConvertType.LowerCaseDash) {
            // 转小写
            newName.append(arr[0].toLowerCase());
        }
        // 1、生成除第一组的字符串
        if (arr.length > 1) {
            for (int i = 1; i < arr.length; i++) {
                if ("".equals(arr[i])) {
                    continue;
                }
                // 添加连接字符
                newName.append(character);
                if (type == ConvertType.BigHump || type == ConvertType.SmallHump) {
                    // 首写字母大写
                    newName.append(capitalizeTheFirstLetter(arr[i]));
                } else if (type == ConvertType.UpperCaseUnderline || type == ConvertType.UpperCaseDash) {
                    // 转大写
                    newName.append(arr[i].toUpperCase());
                } else if (type == ConvertType.LowerCaseUnderline || type == ConvertType.LowerCaseDash) {
                    // 转小写
                    newName.append(arr[i].toLowerCase());
                }
            }
        }
        return newName.toString();
    }

    /**
     * 按规则分离
     *
     * @param name 名称
     * @return 分离结构
     */
    private static String[] split(String name) {
        return name.trim()
            // 只保留字母、数字、下划线、短横线、空白字符
            .replaceAll("[^A-Za-z0-9\\s_-]", "")
            // 去掉开头的数字
            .replaceFirst("\\d*", "")
            // 替换数字\d为_\d_，方便分割
            .replaceAll("(\\d)", "_$1_")
            // 替换大写字母A-Z为_A-Z，方便分割
            .replaceAll("([A-Z])", "_$1")
            // 以空白字符、数字、下划线、短横线分割
            .split("[\\s_-]");
    }

    /**
     * 首写字母大写
     *
     * @param word 单词
     * @return 首写字母大写
     */
    private static String capitalizeTheFirstLetter(String word) {
        return String.valueOf(word.charAt(0)).toUpperCase() + word.substring(1);
    }
}
