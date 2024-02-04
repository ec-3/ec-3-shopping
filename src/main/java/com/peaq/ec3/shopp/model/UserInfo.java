package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Long id;

    private String username;

    private String password;

    private Boolean cardType;

    private String cardNo;

    private String mobile;

    private String email;

    private Boolean gender;

    private Date birthday;

    private Date createTime;

    private Date updateTime;

}