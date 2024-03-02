package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.ProductInfo;

import java.util.List;

import com.peaq.ec3.shopp.request.ProductListReq;
import org.apache.ibatis.annotations.Param;

public interface ProductInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo row);

    int insertSelective(ProductInfo row);

    ProductInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfo row);

    int updateByPrimaryKey(ProductInfo row);

    List<ProductInfo> getProductInfoList(ProductListReq request);
}