package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.AddressChina;

public interface AddressManageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AddressChina row);

    int insertSelective(AddressChina row);

    AddressChina selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AddressChina row);

    int updateByPrimaryKey(AddressChina row);
}