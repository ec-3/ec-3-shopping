package com.peaq.ec3.shopp.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.mapper.ShopCarMapper;
import com.peaq.ec3.shopp.model.ShopCar;
import com.peaq.ec3.shopp.request.OperateCarReq;
import com.peaq.ec3.shopp.request.ListReq;
import com.peaq.ec3.shopp.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RequestMapping("/shopCar")
@RestController
public class ShopCarController {

    @Autowired
    ShopCarMapper shopCarMapper;

    @PostMapping("/list")
    public Result<PageResponse<ShopCar>> list(@RequestBody ListReq request) {
        Page<ShopCar> page = PageHelper.startPage(request.getPageNo(), request.getPageSize())
                .doSelectPage(() -> shopCarMapper.getShopCarList(request.getUserId()));
        return Result.returnSuccess(new PageResponse<>(page.getTotal(), page.getResult()));
    }

    @PostMapping("/operate")
    public Result operate(@RequestBody OperateCarReq operateCar) {
        if (!CollectionUtils.isEmpty(operateCar.getAddCar())) {
            shopCarMapper.addBatch(operateCar.getAddCar());
        }
        if (!CollectionUtils.isEmpty(operateCar.getUpdateCar())) {
            shopCarMapper.updateBatch(operateCar.getUpdateCar());
        }
        if (!CollectionUtils.isEmpty(operateCar.getDelCar())) {
            shopCarMapper.delCarBatch(operateCar.getDelCar().stream().map(ShopCar::getId).collect(Collectors.toList()));
        }
        return Result.returnSuccess();
    }

}
