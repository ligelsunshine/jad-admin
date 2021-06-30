/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.security.controller;

import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import com.jad.common.base.controller.BaseController;
import com.jad.common.constant.RedisConst;
import com.jad.common.lang.Result;
import com.jad.security.entity.dto.LoginWithCaptchaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * 身份认证相关接口
 *
 * @author cxxwl96
 * @since 2021/6/20 12:13
 */
@Api(tags = "身份认证相关接口")
@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    /**
     * 验证码生成器
     */
    @Autowired
    private Producer producer;

    @ApiOperation("获取图片验证码")
    @GetMapping("/captcha")
    public Result captcha() throws IOException {
        // 验证码key
        final String codeKey = UUID.randomUUID().toString();
        // 验证码
        final String codeValue = producer.createText();

        // 生成Base64验证码
        // 生成验证码图片
        final BufferedImage image = producer.createImage(codeValue);
        // 将验证码图片写入到output流
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 将output流转为base64图片码
        final BASE64Encoder encoder = new BASE64Encoder();

        final String base64Img = "data:image/jpeg;base64," + encoder.encode(outputStream.toByteArray());

        // 将Base64验证码存入Redis
        redisUtil.set(RedisConst.SECURITY_LOGIN_CAPTCHA_KEY_PREFIX + codeKey, codeValue, 120);

        final Map<Object, Object> resultMap = MapUtil.builder()
            .put("codeKey", codeKey)
            .put("codeImage", base64Img).build();
        return Result.success(resultMap);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginWithCaptchaDto dto, HttpServletRequest request){
        // 目的是为了在swagger中显示这个接口
        return null;
    }
    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        // 目的是为了在swagger中显示这个接口
        return null;
    }
}
