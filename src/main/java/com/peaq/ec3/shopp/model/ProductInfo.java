package com.peaq.ec3.shopp.model;

import lombok.Data;
import java.util.Date;

@Data
public class ProductInfo {
    private Long id;

    private Long productId;

    private String productName;

    private String model;

    private Long price;

    private Long costPrice;

    private Byte type;

    private Byte colorType;

    private Byte pushStatus;

    private Date productionDate;

    private String describe;

    private Date createTime;

    private Date updateTime;
}