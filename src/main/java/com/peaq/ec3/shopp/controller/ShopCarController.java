package com.peaq.ec3.shopp.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.peaq.ec3.shopp.common.Result;
import com.peaq.ec3.shopp.mapper.ShopCarMapper;
import com.peaq.ec3.shopp.model.ShopCar;
import com.peaq.ec3.shopp.request.ExecuteCartReq;
import com.peaq.ec3.shopp.request.ListReq;
import com.peaq.ec3.shopp.request.OperateCartReq;
import com.peaq.ec3.shopp.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @PostMapping("/cart")
    public Result<Map<Long, List<ShopCar>>> cart(@RequestBody ListReq request) {
        List<ShopCar> shopCarList = shopCarMapper.getShopCarList(request.getUserId());
        if (!CollectionUtils.isEmpty(shopCarList)) {
            Map<Long, List<ShopCar>> map = new HashMap<>();
            for (ShopCar shopCar : shopCarList) {
                if (map.containsKey(shopCar.getBaseId())) {
                    map.get(shopCar.getBaseId()).add(shopCar);
                } else {
                    List<ShopCar> list = new ArrayList<>();
                    list.add(shopCar);
                    map.put(shopCar.getBaseId(), list);
                }
            }
            return Result.returnSuccess(map);
        }
        return Result.returnSuccess();
    }

    @PostMapping("/operate")
    public Result operate(@RequestBody OperateCartReq operateCar) {
        operateCar(operateCar);
        return Result.returnSuccess();
    }


    private void operateCar(OperateCartReq operateCar) {
        if (!CollectionUtils.isEmpty(operateCar.getAddCart())) {
            shopCarMapper.addBatch(operateCar.getAddCart());
        }
        if (!CollectionUtils.isEmpty(operateCar.getUpdateCart())) {
            shopCarMapper.updateBatch(operateCar.getUpdateCart());
        }
        if (!CollectionUtils.isEmpty(operateCar.getDelCart())) {
            shopCarMapper.delCarBatch(operateCar.getDelCart());
        }
    }

    @Transactional
    @PostMapping("/execute")
    public Result execute(@RequestBody ExecuteCartReq cartReq) {
        if (CollectionUtils.isEmpty(cartReq.getCart())) {
            shopCarMapper.delCarByUser(cartReq.getUserId());
        } else {
            Map<Long, ShopCar> shopCars = convert(cartReq.getCart());
            OperateCartReq operate = new OperateCartReq();
            List<ShopCar> shopCarList = shopCarMapper.getShopCarList(cartReq.getUserId());
            if (CollectionUtils.isEmpty(shopCarList)) {
                setAddCar(operate, shopCars);
            } else {
                operate.setDelCart(new ArrayList<>());
                operate.setUpdateCart(new ArrayList<>());
                for (ShopCar cart : shopCarList) {
                    ShopCar car = shopCars.get(cart.getProductId());
                    if (car == null) {
                        operate.getDelCart().add(cart.getId());
                    } else {
                        if (car.getQuantity().intValue() != cart.getQuantity()) {
                            operate.getUpdateCart().add(car);
                        }
                        cartReq.getCart().remove(car.getProductId());
                    }
                }
                setAddCar(operate, shopCars);
            }
            operateCar(operate);
        }
        return Result.returnSuccess();
    }

    private Map<Long, ShopCar> convert(Map<Long, List<ShopCar>> map) {
        Map<Long, ShopCar> result = new HashMap<>();
        map.forEach((k, v) -> {
            for (ShopCar car : v) {
                result.put(car.getId(), car);
            }
        });
        return result;
    }

    private void setAddCar(OperateCartReq operate, Map<Long, ShopCar> shopCars) {
        operate.setAddCart(new ArrayList<>());
        shopCars.forEach((key, value) -> {
            operate.getAddCart().add(value);
        });
    }

}
