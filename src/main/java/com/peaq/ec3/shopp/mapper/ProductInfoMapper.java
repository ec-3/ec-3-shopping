package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.ProductInfo;
import com.peaq.ec3.shopp.request.ProductListReq;

import java.util.List;

public interface ProductInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo row);

    int insertSelective(ProductInfo row);

    ProductInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductInfo row);

    int updateByPrimaryKey(ProductInfo row);

    List<ProductInfo> getProductInfoList(ProductListReq request);
}