package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderItem {
    private Long id;

    private Long userId;

    private String orderId;

    private Long productId;

    private String masterPic;

    private String productName;

    private Integer quantity;

    private Long price;

    //优惠权益后销售单价
    private Long equityPrice;

    private Byte type;

    private Date createTime;

    private Date updateTime;

}