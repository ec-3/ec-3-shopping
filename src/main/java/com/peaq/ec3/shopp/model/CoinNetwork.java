package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class CoinNetwork {
    private Long id;

    private Long coinId;

    private Long netId;

    private Boolean enable;

    private Date createTime;

    private Date updateTime;

}