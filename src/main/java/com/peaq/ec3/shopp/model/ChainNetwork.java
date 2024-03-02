package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class ChainNetwork {
    private Long id;

    private String chainName;

    private String chainId;

    private String networkId;

    private String currency;

    private String walletType;

    private String coinAddr;

    private String explorerUrl;

    private String company;

    private Integer sort;

    private Boolean status;

    private Boolean enable;

    private String transferEstimateTime;

    private Date createTime;

    private Date updateTime;

}