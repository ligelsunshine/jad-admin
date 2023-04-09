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
        // 利用序列化隐藏的writeReplace方法返回值的对象。
        // 如果一个序列化类中含有Object writeReplace()方法，
        // 那么实际序列化的对象将是作为writeReplace方法返回值的对象，
        // 而且序列化过程的依据是实际被序列化对象的序列化实现。
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
