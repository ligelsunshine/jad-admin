/*
 * Copyright (c) 2021-2021, jad (cxxwl96@sina.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jad.system.modules.test.controller;

import com.google.code.kaptcha.Producer;
import com.jad.common.base.controller.BaseController;
import com.jad.common.constant.RedisConst;
import com.jad.common.lang.Result;
import com.jad.common.service.UserService;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private Producer producer;

    @Autowired
    private UserService userService;

    @ApiOperation("生成测试图片验证码验证码")
    @GetMapping("/captcha/generator")
    public Result<?> generatorCaptcha() throws IOException {
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
        return Result.success("生成成功", resultMap);
    }

    @ApiOperation("用户密码加密")
    @GetMapping("/encoder")
    public Result<?> encoder(String username, String password) {
        return Result.success("加密成功", userService.encodeUserPassword(username, password));
    }

    @ApiOperation("yaml配置信息加密")
    @GetMapping("/encrypt")
    public Result<?> encrypt(@RequestParam(defaultValue = "cxxwl96@sina.com") String salt, @RequestParam String text) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        return Result.success("加密成功", encryptor.encrypt(text));
    }

    @ApiOperation("yaml配置信息解密")
    @GetMapping("/decrypt")
    public Result<?> decrypt(@RequestParam(defaultValue = "cxxwl96@sina.com") String salt, @RequestParam String text) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword(salt);
        return Result.success("解密成功", encryptor.decrypt(text));
    }
}
