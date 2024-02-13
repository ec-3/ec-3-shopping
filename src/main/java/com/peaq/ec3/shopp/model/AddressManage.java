package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class AddressManage {
    private Long id;
    private Byte defFlag;

    private String userId;

    private String recipient;

    private String mobile;

    private String province;

    private String city;

    private String district;

    private String address;

    private Byte label;

    private String createOperator;

    private Date createTime;

    private String updateOperator;

    private Date updateTime;

    private String provinceAreaCode;

    private String cityAreaCode;

    private String districtAreaCode;

}