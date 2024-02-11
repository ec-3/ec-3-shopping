package com.peaq.ec3.shopp.uitls;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.peaq.ec3.shopp.common.ReturnEnum;
import com.peaq.ec3.shopp.exception.Ec3Exception;
import com.peaq.ec3.shopp.request.UserLogin;

import java.util.Calendar;

public class JWTHelper {

    private static final String SELECT = "ec3";

    public static String createToken(UserLogin user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);// 设置一个7天的时间段
        return JWT.create()
                .withClaim("username", user.getUsername()) // 载荷
                .withExpiresAt(calendar.getTime()) // 过期时间
                .sign(Algorithm.HMAC256(SELECT)); // 签名
    }

    public static boolean verifyToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SELECT)).build().verify(token);
            return true;
        } catch (Exception e) {
            throw new Ec3Exception(ReturnEnum.Login.NOT_ACCESS.getCode(), ReturnEnum.Login.NOT_ACCESS.getMsg());
        }
    }
}
