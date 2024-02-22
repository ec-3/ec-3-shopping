package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OrderItem row);

    int insertSelective(OrderItem row);

    OrderItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItem row);

    int updateByPrimaryKey(OrderItem row);

    List<OrderItem> getOrderItemList(@Param("userId") Long userId,@Param("orderId")  String orderId);

    int addBatch(@Param("orderItems") List<OrderItem> orderItems);
}