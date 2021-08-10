/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.common.utils;

import com.jad.common.exception.BadRequestException;
import com.jad.common.lang.Result;

import org.springframework.validation.BindingResult;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 * 实体校验工具类
 *
 * @author cxxwl96
 * @since 2021/8/9 23:54
 */
public class ValidatorUtil {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     */
    public static void validateEntity(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            constraintViolations.forEach(violation -> {
                throw new BadRequestException(Result.failed(violation.getMessage()));
            });
        }
    }

    /**
     * 校验结果
     *
     * @param bindingResult 校验结果
     */
    public static void validateEntity(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(validator -> {
                throw new BadRequestException(Result.failed(validator.getDefaultMessage()));
            });
        }
    }
}
