package com.peaq.ec3.shopp.request;

import com.peaq.ec3.shopp.model.Order;
import com.peaq.ec3.shopp.model.OrderItem;
import lombok.Data;

import java.util.List;

@Data
public class SubmitOrderReq {
    private Order order;
    private List<OrderItem> orderItems;
    private List<Long> carIds;
}
