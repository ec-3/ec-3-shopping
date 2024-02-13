package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.UserInfo;
import com.peaq.ec3.shopp.request.UserLogin;

public interface UserInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(UserInfo row);

    int insertSelective(UserInfo row);

    UserInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserInfo row);

    int updateByPrimaryKey(UserInfo row);

    UserInfo getUserByLogin(UserLogin user);
}