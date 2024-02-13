package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class ProductInfo {
    private Long id;

    private String productNum;

    private String productName;

    private String brand;

    private String specification;

    private String supplierId;

    private Long price;

    private Long costPrice;

    private Byte type;

    private Byte colorType;

    private Byte publishStatus;

    private Date productionDate;

    private String describe;

    private Date createTime;

    private Date updateTime;

}