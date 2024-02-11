package com.peaq.ec3.shopp.controller;

import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.common.ReturnMsg;
import com.peaq.ec3.shopp.exception.Ec3Exception;
import com.peaq.ec3.shopp.mapper.UserInfoMapper;
import com.peaq.ec3.shopp.model.UserInfo;
import com.peaq.ec3.shopp.request.UserLogin;
import com.peaq.ec3.shopp.response.UserRes;
import com.peaq.ec3.shopp.uitls.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/userInfo")
@RestController
public class UserInfoController {

    @Autowired
    UserInfoMapper userInfoMapper;

    @PostMapping("/login")
    public Result<UserRes> login(@RequestBody UserLogin user) {
        UserInfo userInfo = getUserInfo(user);
        if (userInfo == null) {
            throw new Ec3Exception(ReturnMsg.SIGN_INFO_ERR);
        }
        String token = JWTHelper.createToken(user);
        return Result.returnSuccess(new UserRes(userInfo,token));
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserLogin user) {
        UserInfo userInfo = getUserInfo(user);
        if (userInfo != null) {
            throw new Ec3Exception(ReturnMsg.USER_HAS);
        }
        userInfoMapper.insertSelective(userInfo);
        return Result.returnSuccess();
    }

    @PostMapping("/signOut")
    public Result signOut(@RequestBody UserLogin user) {

        return Result.returnSuccess();
    }

    private UserInfo getUserInfo(UserLogin user) {
        if (StringUtils.isEmpty(user.getEmail()) && StringUtils.isEmpty(user.getUsername())) {
            throw new Ec3Exception(ReturnMsg.TWO_EMPTY);
        }
        if (!StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getPassword())) {
            throw new Ec3Exception(ReturnMsg.PASS_NOT_BLANK);
        }
        return userInfoMapper.getUserByLogin(user);
    }

    @GetMapping("/test")
    public String test() {

        return "哈哈哈哈哈";
    }

}
