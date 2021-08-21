package com.jad.common.entity;

import com.alibaba.fastjson.JSONArray;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import cn.hutool.core.io.FileUtil;

class TreeTest {
    private Tree<Menu> tree;

    @BeforeEach
    void setUp() {
        final String basePath = System.getProperty("user.dir") + "/../doc/json/MenuList.json";
        final String json = FileUtil.readUtf8String(basePath);
        final List<Menu> menuList = JSONArray.parseArray(json, Menu.class);
        tree = new Tree<>(menuList, null);
    }

    @Test
    void getRootTree() {
        final List<Menu> rootTree = tree.getRootTree();
    }

    @Test
    void getCodeTree() {
        final Menu codeTree = tree.getCodeTree("ICON");
    }

    @Test
    void getSubTree() {
        final Menu subTree = tree.getSubTree("be23e50d8fcd326a6d2819cb7837ec39");
    }

    @Test
    void getSubList() {
        final List<Menu> subList = tree.getSubList("be23e50d8fcd326a6d2819cb7837ec39");
    }

    @Test
    void getChildrenTree() {
        final List<Menu> childrenTree = tree.getChildrenTree("dd5c824b9f0f61a6cd3a2b703bdffc55");
    }

    @Test
    void getChildrenList() {
        final List<Menu> childrenList = tree.getChildrenList("dd5c824b9f0f61a6cd3a2b703bdffc55"); // TODO 有问题
    }

    @Test
    void toList() {
        final List<Menu> childrenTree = tree.getChildrenTree("dd5c824b9f0f61a6cd3a2b703bdffc55");
        final List<Menu> list = tree.toList(childrenTree);
    }

    @Test
    void testToList() {
        final Menu subTree = tree.getSubTree("dd5c824b9f0f61a6cd3a2b703bdffc55");
        final List<Menu> list = tree.toList(subTree);
        final Menu subTree1 = tree.getSubTree("dd5c824b9f0f61a6cd3a2b703bdffc55");
    }
}