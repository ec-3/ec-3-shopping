package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.ProductInfo;
import com.peaq.ec3.shopp.model.ProductInfoExample;
import java.util.List;

import com.peaq.ec3.shopp.request.ProductListReq;
import org.apache.ibatis.annotations.Param;

public interface ProductInfoMapper {
    long countByExample(ProductInfoExample example);

    int deleteByExample(ProductInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductInfo row);

    int insertSelective(ProductInfo row);

    List<ProductInfo> selectByExample(ProductInfoExample example);

    ProductInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ProductInfo row, @Param("example") ProductInfoExample example);

    int updateByExample(@Param("row") ProductInfo row, @Param("example") ProductInfoExample example);

    int updateByPrimaryKeySelective(ProductInfo row);

    int updateByPrimaryKey(ProductInfo row);

    List<ProductInfo> getProductInfoList(ProductListReq request);
}