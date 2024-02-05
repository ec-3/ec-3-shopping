package com.peaq.ec3.shopp.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.mapper.OrderItemMapper;
import com.peaq.ec3.shopp.model.OrderItem;
import com.peaq.ec3.shopp.request.ListReq;
import com.peaq.ec3.shopp.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/orderItem")
@RestController
public class OrderItemController {

    @Autowired
    OrderItemMapper orderItemMapper;

    @GetMapping("/list")
    public Result<PageResponse<OrderItem>> list(@RequestBody ListReq request) {
        Page<OrderItem> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())
                .doSelectPage(() -> orderItemMapper.getOrderItemList(request.getUserId(),request.getOrderId()));
        return Result.returnSuccess(new PageResponse<>(page.getTotal(), page.getResult()));
    }
}
