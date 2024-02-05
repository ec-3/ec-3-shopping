package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.OrderItem;
import com.peaq.ec3.shopp.model.OrderItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
    long countByExample(OrderItemExample example);

    int deleteByExample(OrderItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderItem row);

    int insertSelective(OrderItem row);

    List<OrderItem> selectByExample(OrderItemExample example);

    OrderItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") OrderItem row, @Param("example") OrderItemExample example);

    int updateByExample(@Param("row") OrderItem row, @Param("example") OrderItemExample example);

    int updateByPrimaryKeySelective(OrderItem row);

    int updateByPrimaryKey(OrderItem row);

    List<OrderItem> getOrderItemList(@Param("userId") String userId,@Param("orderId")  String orderId);
}