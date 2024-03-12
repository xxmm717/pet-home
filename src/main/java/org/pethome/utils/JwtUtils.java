package org.pethome.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    //TOKEN的有效期
    private static final int TOKE_TIME_OUT = 1 * 3600*3600;
    //加密KEY
    private static final String TOKE_SECRET = "xaiolanshu";

    //生成TOKEN
    public static String getToken(Map params) {
        long currentTimeMillis = System.currentTimeMillis();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, TOKE_SECRET)//加密方式
                .setExpiration(new Date(currentTimeMillis + TOKE_TIME_OUT * 1000))//过期时间戳
                .addClaims(params)
                .compact();
    }

    /**
     * 获取Token中的claim信息
     */
    public static Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey("xiaolanshu")
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    /**
     * 是否有效
     */
    public static boolean verifyToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("xiaolanshu")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return false;

        }
        return true;
    }
}
