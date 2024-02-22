package com.peaq.ec3.shopp.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.common.ReturnMsg;
import com.peaq.ec3.shopp.exception.Ec3Exception;
import com.peaq.ec3.shopp.mapper.OrderItemMapper;
import com.peaq.ec3.shopp.mapper.OrderMapper;
import com.peaq.ec3.shopp.mapper.ShopCarMapper;
import com.peaq.ec3.shopp.model.Order;
import com.peaq.ec3.shopp.model.OrderItem;
import com.peaq.ec3.shopp.request.ListReq;
import com.peaq.ec3.shopp.request.SubmitOrderReq;
import com.peaq.ec3.shopp.response.PageResponse;
import com.peaq.ec3.shopp.uitls.CpIdCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    ShopCarMapper shopCarMapper;
    @Autowired
    CpIdCreator cpIdCreator;

    @PostMapping("/list")
    public Result<PageResponse<Order>> list(@RequestBody ListReq request) {
        Page<Order> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())
                .doSelectPage(() -> orderMapper.getOrderList(request.getUserId()));
        return Result.returnSuccess(new PageResponse<>(page.getTotal(), page.getResult()));
    }

    @PostMapping("/save")
    public Result save(@RequestBody Order order) {
        orderMapper.insertSelective(order);
        return Result.returnSuccess();
    }

    @PostMapping("/update")
    public Result update(@RequestBody Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
        return Result.returnSuccess();
    }

    @Transactional
    @PostMapping("/submit")
    public Result submitOrder(@RequestBody SubmitOrderReq orderReq) {
        Order order = orderReq.getOrder();
        order.setOrderId(cpIdCreator.createOrderId());
        orderMapper.insertSelective(order);
        List<OrderItem> orderItems = orderReq.getOrderItems();
        if (CollectionUtils.isEmpty(orderItems)) {
            throw new Ec3Exception(String.format(ReturnMsg.DATA_EMPTY, "orderItems"));
        }
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(order.getOrderId());
        }
        orderItemMapper.addBatch(orderItems);
        if (!CollectionUtils.isEmpty(orderReq.getCarIds())) {
            shopCarMapper.delCarBatch(orderReq.getCarIds());
        }
        return Result.returnSuccess();
    }


}
