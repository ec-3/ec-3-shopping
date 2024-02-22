package com.peaq.ec3.shopp.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Long id;

    private String orderId;

    private Long userId;

    private Long amId;

    private String userName;

    private Long totalAmount;

    private Long couponDiscount;

    private Long payAmount;

    private Long actualPayAmount;

    private Byte payType;

    private String chainId;

    private String chainTx;

    private String recipient;

    private String mobile;

    private Byte invoiceType;

    private String company;

    private String taxpayerNum;

    private String companyEmail;

    private String companyPhone;

    private String bankCard;

    private String openAccountBank;

    private String invoiceRecipient;

    private String invoiceRecipientContact;

    private String invoiceRecipientEmail;

    private String invoiceAdditionalInfo;

    private Byte status;
    private Byte submitType;

    private Date payTime;

    private Byte deliveryStatus;

    private Date cancelTime;

    private String createOperator;

    private Date createTime;

    private String updateOperator;

    private Date updateTime;

}