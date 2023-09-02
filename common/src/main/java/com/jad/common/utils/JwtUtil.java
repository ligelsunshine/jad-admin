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

package com.jad.common.utils;

import com.jad.common.constant.RedisConst;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

/**
 * JwtUtil
 *
 * @author cxxwl96
 * @since 2021/6/20 21:34
 */
@Data
@Component
@ConfigurationProperties(prefix = "jad.jwt")
public class JwtUtil {

    // 过期时间（单位：s）
    private long expireSeconds;

    // jwt秘钥
    private String secret;

    // 请求头名称
    private String header;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 生成token
     *
     * @param username 用户名
     * @return token
     */
    public String generateToken(String username) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expireSeconds * 1000);
        String token = Jwts.builder()
            .setHeaderParam("type", "JWT")
            .setSubject(username)
            .setIssuedAt(nowDate)
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
        // 放到redis里面，过期时间为jwt过期时间
        redisUtil.set(RedisConst.SECURITY_USER_AUTHENTICATE_TOKEN_KEY_PREFIX + username, token, expireSeconds);
        return token;
    }

    /**
     * 解析token，得到claim
     *
     * @param token token
     * @return claims
     */
    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @param token token
     * @return token是否过期
     */
    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        if (claims == null) {
            return true;
        }
        return claims.getExpiration().before(new Date());
    }
}
