package com.peaq.ec3.shopp.model;

import java.util.Date;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getDefFlag() {
        return defFlag;
    }

    public void setDefFlag(Byte defFlag) {
        this.defFlag = defFlag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getLabel() {
        return label;
    }

    public void setLabel(Byte label) {
        this.label = label;
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

    public String getProvinceAreaCode() {
        return provinceAreaCode;
    }

    public void setProvinceAreaCode(String provinceAreaCode) {
        this.provinceAreaCode = provinceAreaCode;
    }

    public String getCityAreaCode() {
        return cityAreaCode;
    }

    public void setCityAreaCode(String cityAreaCode) {
        this.cityAreaCode = cityAreaCode;
    }

    public String getDistrictAreaCode() {
        return districtAreaCode;
    }

    public void setDistrictAreaCode(String districtAreaCode) {
        this.districtAreaCode = districtAreaCode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", defFlag=").append(defFlag);
        sb.append(", userId=").append(userId);
        sb.append(", recipient=").append(recipient);
        sb.append(", mobile=").append(mobile);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", district=").append(district);
        sb.append(", address=").append(address);
        sb.append(", label=").append(label);
        sb.append(", createOperator=").append(createOperator);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateOperator=").append(updateOperator);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", provinceAreaCode=").append(provinceAreaCode);
        sb.append(", cityAreaCode=").append(cityAreaCode);
        sb.append(", districtAreaCode=").append(districtAreaCode);
        sb.append("]");
        return sb.toString();
    }
}