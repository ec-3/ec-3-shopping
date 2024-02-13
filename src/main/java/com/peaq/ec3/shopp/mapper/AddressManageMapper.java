package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.AddressManage;

public interface AddressManageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AddressManage row);

    int insertSelective(AddressManage row);

    AddressManage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AddressManage row);

    int updateByPrimaryKey(AddressManage row);
}