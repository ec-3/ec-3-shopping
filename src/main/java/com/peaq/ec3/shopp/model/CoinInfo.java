package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class CoinInfo {
    private Long id;

    private String currency;

    private String currencyFullName;

    private String price;

    private String icon;

    private String company;

    private String companyUrl;

    private Integer sort;

    private Boolean marketType;

    private String scale;

    private Boolean enable;

    private Date createTime;

    private Date updateTime;

}