package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.AddressGlobal;

import java.util.List;

public interface AddressGlobalMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AddressGlobal row);

    int insertSelective(AddressGlobal row);

    AddressGlobal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AddressGlobal row);

    int updateByPrimaryKey(AddressGlobal row);

    List<AddressGlobal> list(Long userId);
}