package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.CoinInfo;

import java.util.List;

public interface CoinInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CoinInfo row);

    int insertSelective(CoinInfo row);

    CoinInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoinInfo row);

    int updateByPrimaryKey(CoinInfo row);

    List<CoinInfo> list();
}