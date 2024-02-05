package com.peaq.ec3.shopp.request;

import com.peaq.ec3.shopp.model.ShopCar;
import lombok.Data;

import java.util.List;

@Data
public class OperateCarReq {

    private List<ShopCar> delCar;
    private List<ShopCar> addCar;
    private List<ShopCar> updateCar;

}
