package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.Merchant;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Merchant row);

    int insertSelective(Merchant row);

    Merchant selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Merchant row);

    int updateByPrimaryKey(Merchant row);
}