package com.peaq.ec3.shopp.controller;

import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.exception.Ec3Exception;
import com.peaq.ec3.shopp.mapper.UserInfoMapper;
import com.peaq.ec3.shopp.model.UserInfo;
import com.peaq.ec3.shopp.request.UserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/userInfo")
@RestController
public class UserInfoController {

    @Autowired
    UserInfoMapper userInfoMapper;

    @PostMapping("/login")
    public Result login(@RequestBody UserLogin user, HttpSession session) {
        UserInfo userInfo = getUserInfo(user);
        if (userInfo == null) {
            throw new Ec3Exception("登录信息有误！");
        }
        session.setAttribute("user", userInfo);
        return Result.returnSuccess();
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserLogin user) {
        UserInfo userInfo = getUserInfo(user);
        if (userInfo != null) {
            throw new Ec3Exception("用户已注册！");
        }
        userInfoMapper.insertSelective(userInfo);
        return Result.returnSuccess();
    }

    private UserInfo getUserInfo(UserLogin user) {
        if (StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getUsername())) {
            throw new Ec3Exception("用户名或email不能同时为空！");
        }
        if (!StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getPassword())) {
            throw new Ec3Exception("密码不能为空！");
        }
        return userInfoMapper.getUserByLogin(user);
    }

}
