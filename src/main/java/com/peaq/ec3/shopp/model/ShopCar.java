package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class ShopCar {
    private Long id;

    private Long userId;

    private Long productId;

    private Integer quantity;

    private Boolean delFlag;

    private Date createTime;

    private Date updateTime;

}