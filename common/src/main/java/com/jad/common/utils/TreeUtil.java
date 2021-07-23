/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;

/**
 * TreeUtil
 *
 * @author cxxwl96
 * @since 2021/7/22 22:38
 */
public class TreeUtil {
    public static <T> List<T> longTree(List<T> list) {
        return createTree(list, "id", "pId", "children", 0L);
    }

    public static <T> List<T> intTree(List<T> list) {
        return createTree(list, "id", "pId", "children", 0);
    }

    public static <T> List<T> stringTree(List<T> list) {
        return createTree(list, "id", "pId", "children", "0");
    }

    public static <T> List<T> createTree(List<T> list, String idName, String pIdName, String childName,
        Object rootIdValue) {
        if (CollUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        Class<?> aClass = list.get(0).getClass();
        Map<String, Field> vFieldMap = getAllFieldMap(aClass);
        Field idField = vFieldMap.get(idName);
        if (idField == null) {
            throw new IllegalArgumentException("找不到 " + idName + "属性");
        }
        Field pidField = vFieldMap.get(pIdName);
        if (pidField == null) {
            throw new IllegalArgumentException("找不到 " + pIdName + "属性");
        }
        Field childField = vFieldMap.get(childName);
        if (childField == null) {
            throw new IllegalArgumentException("找不到 " + childName + "属性");
        }
        Map<Object, List<T>> map = new HashMap<>(8);
        for (T t : list) {
            Object value = null;
            try {
                value = pidField.get(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            List<T> objects = map.get(value);
            if (CollUtil.isEmpty(objects)) {
                objects = new ArrayList<>();
                map.put(value, objects);
            }
            objects.add(t);
        }
        List<T> objectList = new ArrayList<>();
        List<T> topList = map.get(rootIdValue);
        if (CollUtil.isNotEmpty(topList)) {
            for (T o : topList) {
                objectList.add(addChildNode(o, map, idField, childField));
            }
        }
        return objectList;
    }

    private static <T> T addChildNode(T treeNode, Map<Object, List<T>> map, Field idField, Field childField) {
        try {
            List<T> objectList = map.get(idField.get(treeNode));
            if (CollUtil.isNotEmpty(objectList)) {
                List<T> treeNodeList = new ArrayList<>();
                for (T t : objectList) {
                    treeNodeList.add(addChildNode(t, map, idField, childField));
                }
                childField.set(treeNode, treeNodeList);
            }
            return treeNode;
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static Map<String, Field> getAllFieldMap(Class<?> cls) {
        Field[] vFields = getAllFields(cls);
        Map<String, Field> fieldMap = new HashMap<>(vFields.length);
        for (Field field : vFields) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    private static Field[] getAllFields(final Class<?> cls) {
        final List<Field> allFieldsList = getAllFieldsList(cls);
        return allFieldsList.toArray(new Field[0]);
    }

    private static List<Field> getAllFieldsList(final Class<?> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("The class must not be null");
        }
        final List<Field> allFields = new ArrayList<>();
        Class<?> currentClass = cls;
        while (currentClass != null) {
            final Field[] declaredFields = currentClass.getDeclaredFields();
            allFields.addAll(Arrays.asList(declaredFields));
            currentClass = currentClass.getSuperclass();
        }
        return allFields;
    }
}