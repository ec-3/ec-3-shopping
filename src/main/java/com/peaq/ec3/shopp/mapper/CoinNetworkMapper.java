package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.CoinNetwork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoinNetworkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CoinNetwork row);

    int insertSelective(CoinNetwork row);

    CoinNetwork selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CoinNetwork row);

    int updateByPrimaryKey(CoinNetwork row);

    List<CoinNetwork> batchByIds(@Param("ids") List<Long> ids);
}