package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.ProductPic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductPicMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ProductPic row);

    int insertSelective(ProductPic row);

    ProductPic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductPic row);

    int updateByPrimaryKey(ProductPic row);

    List<ProductPic> getPicByPIds(@Param("ids") List<Long> ids);
}