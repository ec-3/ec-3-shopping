package com.peaq.ec3.shopp.model;

import java.util.Date;

public class Order {
    private Long id;

    private String orderId;

    private String userId;

    private String amId;

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

    private Date payTime;

    private Byte deliveryStatus;

    private Date cancelTime;

    private String createOperator;

    private Date createTime;

    private String updateOperator;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAmId() {
        return amId;
    }

    public void setAmId(String amId) {
        this.amId = amId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Long couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Long getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(Long actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getChainTx() {
        return chainTx;
    }

    public void setChainTx(String chainTx) {
        this.chainTx = chainTx;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Byte getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(Byte invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTaxpayerNum() {
        return taxpayerNum;
    }

    public void setTaxpayerNum(String taxpayerNum) {
        this.taxpayerNum = taxpayerNum;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getOpenAccountBank() {
        return openAccountBank;
    }

    public void setOpenAccountBank(String openAccountBank) {
        this.openAccountBank = openAccountBank;
    }

    public String getInvoiceRecipient() {
        return invoiceRecipient;
    }

    public void setInvoiceRecipient(String invoiceRecipient) {
        this.invoiceRecipient = invoiceRecipient;
    }

    public String getInvoiceRecipientContact() {
        return invoiceRecipientContact;
    }

    public void setInvoiceRecipientContact(String invoiceRecipientContact) {
        this.invoiceRecipientContact = invoiceRecipientContact;
    }

    public String getInvoiceRecipientEmail() {
        return invoiceRecipientEmail;
    }

    public void setInvoiceRecipientEmail(String invoiceRecipientEmail) {
        this.invoiceRecipientEmail = invoiceRecipientEmail;
    }

    public String getInvoiceAdditionalInfo() {
        return invoiceAdditionalInfo;
    }

    public void setInvoiceAdditionalInfo(String invoiceAdditionalInfo) {
        this.invoiceAdditionalInfo = invoiceAdditionalInfo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Byte getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Byte deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCreateOperator() {
        return createOperator;
    }

    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", amId=").append(amId);
        sb.append(", userName=").append(userName);
        sb.append(", totalAmount=").append(totalAmount);
        sb.append(", couponDiscount=").append(couponDiscount);
        sb.append(", payAmount=").append(payAmount);
        sb.append(", actualPayAmount=").append(actualPayAmount);
        sb.append(", payType=").append(payType);
        sb.append(", chainId=").append(chainId);
        sb.append(", chainTx=").append(chainTx);
        sb.append(", recipient=").append(recipient);
        sb.append(", mobile=").append(mobile);
        sb.append(", invoiceType=").append(invoiceType);
        sb.append(", company=").append(company);
        sb.append(", taxpayerNum=").append(taxpayerNum);
        sb.append(", companyEmail=").append(companyEmail);
        sb.append(", companyPhone=").append(companyPhone);
        sb.append(", bankCard=").append(bankCard);
        sb.append(", openAccountBank=").append(openAccountBank);
        sb.append(", invoiceRecipient=").append(invoiceRecipient);
        sb.append(", invoiceRecipientContact=").append(invoiceRecipientContact);
        sb.append(", invoiceRecipientEmail=").append(invoiceRecipientEmail);
        sb.append(", invoiceAdditionalInfo=").append(invoiceAdditionalInfo);
        sb.append(", status=").append(status);
        sb.append(", payTime=").append(payTime);
        sb.append(", deliveryStatus=").append(deliveryStatus);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", createOperator=").append(createOperator);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateOperator=").append(updateOperator);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}