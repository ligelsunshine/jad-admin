/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * FileUtil
 *
 * @author cxxwl96
 * @since 2021/7/22 22:41
 */
public class FileUtil {
    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        // 创建MessageDigest对象，添加MD5处理
        MessageDigest digest;
        FileInputStream in;
        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            // 读取图片
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        // 返回16进制表示形式
        return bigInt.toString(16);
    }
}