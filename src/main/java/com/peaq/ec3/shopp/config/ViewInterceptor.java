package com.peaq.ec3.shopp.config;

import com.peaq.ec3.shopp.common.ReturnEnum;
import com.peaq.ec3.shopp.common.ReturnMsg;
import com.peaq.ec3.shopp.exception.Ec3Exception;
import com.peaq.ec3.shopp.uitls.JWTHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ViewInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(ReturnMsg.AUTHORIZATION);
        if (StringUtils.isEmpty(token)){
            throw new Ec3Exception(ReturnEnum.Login.PLEASE_SIGN.getCode(),ReturnEnum.Login.PLEASE_SIGN.getMsg());
        }
        return JWTHelper.verifyToken(token);
    }
}
