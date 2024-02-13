package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class OrderItem {
    private Long id;

    private String userId;

    private String orderId;

    private String productNum;

    private String masterPic;

    private String productName;

    private Integer quantity;

    private Long price;

    private Long equityPrice;

    private Long costPrice;

    private Byte type;

    private Date createTime;

    private Date updateTime;

}