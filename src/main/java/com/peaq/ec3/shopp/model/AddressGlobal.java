package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class AddressGlobal {
    private Long id;

    private Byte defFlag;

    private Long userId;

    private String recipient;

    private String phone;

    private String country;

    private String city;

    private String street;

    private String county;

    private String postcode;

    private String email;

    private String address;

    private String label;

    private String createOperator;

    private Date createTime;

    private String updateOperator;

    private Date updateTime;

}