/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * JsonUtil
 *
 * @author cxxwl96
 * @since 2021/6/21 00:32
 */
public class JsonUtil {
    /**
     * 序列化为json字符串
     *
     * @param obj 泛型对象
     * @return json字符串
     */
    public static String toJSONString(Object obj) {
        return JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue);
    }

    /**
     * 反序列化为对象
     *
     * @param json json字符串
     * @return 对象
     */
    public static <T> T parseJson(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }
}
