package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.Order;

import java.util.List;

public interface OrderMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Order row);

    int insertSelective(Order row);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order row);

    int updateByPrimaryKey(Order row);

    List<Order> getOrderList(String userId);
}