package com.peaq.ec3.shopp.request;

import com.peaq.ec3.shopp.model.ShopCar;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ExecuteCartReq {
    private Long userId;
    private Map<Long, List<ShopCar>> cart;
}
