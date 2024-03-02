package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.ChainNetwork;

import java.util.List;

public interface ChainNetworkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ChainNetwork row);

    int insertSelective(ChainNetwork row);

    ChainNetwork selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ChainNetwork row);

    int updateByPrimaryKey(ChainNetwork row);

    List<ChainNetwork> list();
}