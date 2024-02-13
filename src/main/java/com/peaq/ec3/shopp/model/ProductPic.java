package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProductPic {
    private Long id;

    private Long productId;

    private String picDesc;

    private String picUrl;

    private Byte isMaster;

    private Byte picOrder;

    private Byte picStatus;

    private Date createTime;

    private Date updateTime;
}