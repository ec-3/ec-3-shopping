package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.UserInfo;
import com.peaq.ec3.shopp.model.UserInfoExample;
import java.util.List;

import com.peaq.ec3.shopp.request.UserLogin;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {
    long countByExample(UserInfoExample example);

    int deleteByExample(UserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfo row);

    int insertSelective(UserInfo row);

    List<UserInfo> selectByExample(UserInfoExample example);

    UserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") UserInfo row, @Param("example") UserInfoExample example);

    int updateByExample(@Param("row") UserInfo row, @Param("example") UserInfoExample example);

    int updateByPrimaryKeySelective(UserInfo row);

    int updateByPrimaryKey(UserInfo row);

    UserInfo getUserByLogin(UserLogin user);
}