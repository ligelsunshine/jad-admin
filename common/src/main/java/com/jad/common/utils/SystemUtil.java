/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.utils;

import com.jad.common.exception.BadRequestException;
import com.jad.common.lang.Result;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import cn.hutool.core.util.StrUtil;
import cn.hutool.system.SystemPropsKeys;

/**
 * SystemUtil
 *
 * @author cxxwl96
 * @since 2021/9/12 12:40
 */
public class SystemUtil {

    /**
     * 获取操作系统用户名
     * - 优先获取Git用户名
     * - 其次获取操作系统用户名
     *
     * @return 用户名
     * @throws BadRequestException
     */
    public static String getUsername() throws BadRequestException {
        Result result = Result.success();
        String author;
        Process exec = null;
        InputStream is = null;
        try {
            final Runtime runtime = Runtime.getRuntime();
            exec = runtime.exec("git config --global user.name");
            is = exec.getInputStream();
            final Scanner scanner = new Scanner(is);
            author = scanner.nextLine();
            if (StrUtil.isBlank(author)) {
                author = System.getProperty(SystemPropsKeys.USER_NAME);
            }
        } catch (IOException e) {
            author = System.getProperty(SystemPropsKeys.USER_NAME);
        } finally {
            try {
                assert is != null;
                is.close();
                exec.destroy();
            } catch (IOException e) {
                result = Result.failed(e.getMessage());
            }
        }
        if (result.getCode() == Result.CODE_FAILED) {
            throw new BadRequestException(result);
        }
        return author;
    }
}
