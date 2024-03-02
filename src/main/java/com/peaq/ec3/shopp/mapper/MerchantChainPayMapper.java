package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.MerchantChainPay;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantChainPayMapper {

    int deleteByPrimaryKey(Long id);

    int insert(MerchantChainPay row);

    int insertSelective(MerchantChainPay row);

    MerchantChainPay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MerchantChainPay row);

    int updateByPrimaryKey(MerchantChainPay row);

    List<MerchantChainPay> list();
}