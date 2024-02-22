package com.peaq.ec3.shopp.request;

import com.peaq.ec3.shopp.model.ShopCar;
import lombok.Data;

import java.util.List;

@Data
public class OperateCartReq {

    private List<Long> delCart;
    private List<ShopCar> addCart;
    private List<ShopCar> updateCart;

}
