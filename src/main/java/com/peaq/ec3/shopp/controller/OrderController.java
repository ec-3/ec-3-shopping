package com.peaq.ec3.shopp.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.mapper.OrderMapper;
import com.peaq.ec3.shopp.model.Order;
import com.peaq.ec3.shopp.request.ListReq;
import com.peaq.ec3.shopp.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/list")
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


}
