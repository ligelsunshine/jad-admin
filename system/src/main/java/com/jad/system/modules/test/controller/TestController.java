/*
 * Copyright (C), 2021-2021, jad
 */

package com.jad.system.modules.test.controller;

import com.google.code.kaptcha.Producer;
import com.jad.common.base.controller.BaseController;
import com.jad.common.constant.RedisConst;
import com.jad.common.lang.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import cn.hutool.core.map.MapUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sun.misc.BASE64Encoder;

/**
 * TestController
 *
 * @author cxxwl96
 * @since 2021/6/18 22:41
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 验证码生成器
     */
    @Autowired
    private Producer producer;

    @ApiOperation("生成测试图片验证码验证码")
    @GetMapping("/captcha/generator")
    public Result generatorCaptcha() throws IOException {
        String codeKey = "key";
        String codeValue = "value";

        // 生成Base64验证码
        // 生成验证码图片
        final BufferedImage image = producer.createImage(codeValue);
        // 将验证码图片写入到output流
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 将output流转为base64图片码
        final BASE64Encoder encoder = new BASE64Encoder();

        final String base64Img = "data:image/jpeg;base64," + encoder.encode(outputStream.toByteArray());

        redisUtil.set(RedisConst.SECURITY_LOGIN_CAPTCHA_KEY_PREFIX + codeKey, codeValue);

        final Map<Object, Object> resultMap = MapUtil.builder()
            .put("codeKey", codeKey)
            .put("codeValue", codeValue)
            .put("codeImage", base64Img)
            .build();
        return Result.success(resultMap);
    }

    @ApiOperation("密码加密")
    @GetMapping("/encoder")
    public Result encoder(String password) {
        return Result.success(bCryptPasswordEncoder.encode(password));
    }

}
