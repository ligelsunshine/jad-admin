/*
 * Copyright (C), 2021-2021, jad
 */
package com.jad.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

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

    // 过期时间（单位：ms）
    private long expireMilliseconds;

    // jwt秘钥
    private String secret;

    // 请求头名称
    private String header;

    /**
     * 生成token
     *
     * @param username 用户名
     * @return token
     */
    public String generateToken(String username) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expireMilliseconds);
        return Jwts.builder()
            .setHeaderParam("type", "JWT")
            .setSubject(username)
            .setIssuedAt(nowDate)
            .setExpiration(expireDate)
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    /**
     * 解析token，得到claim
     *
     * @param token token
     * @return claims
     */
    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @param claims claims
     * @return token是否过期
     */
    public boolean isExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }
}
