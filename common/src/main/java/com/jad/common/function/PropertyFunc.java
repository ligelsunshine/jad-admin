/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.function;

import com.jad.common.utils.NamingUtil;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.function.Function;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.ReflectUtil;
import lombok.SneakyThrows;

/**
 * lambda方法引用获取字段属性
 *
 * @author cxxwl96
 * @since 2021/8/22 16:23
 */
public interface PropertyFunc<T, R> extends Function<T, R>, Serializable {
    @SneakyThrows
    default String getFieldName() {
        Method method = ReflectUtil.getMethodByName(this.getClass(), "writeReplace");
        method.setAccessible(true);
        SerializedLambda serializedLambda = (SerializedLambda) method.invoke(this);
        String methodName = serializedLambda.getImplMethodName();
        if (methodName.startsWith("get")) {
            methodName = methodName.substring(3);
        } else if (methodName.startsWith("is")) {
            methodName = methodName.substring(2);
        }
        // 首字母变小写
        return CharSequenceUtil.lowerFirst(methodName);
    }

    @SneakyThrows
    default String getColumnName() {
        return NamingUtil.toLowerCaseUnderline(getFieldName());
    }
}
