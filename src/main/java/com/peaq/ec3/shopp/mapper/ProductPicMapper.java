package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.ProductPic;
import com.peaq.ec3.shopp.model.ProductPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductPicMapper {
    long countByExample(ProductPicExample example);

    int deleteByExample(ProductPicExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProductPic row);

    int insertSelective(ProductPic row);

    List<ProductPic> selectByExample(ProductPicExample example);

    ProductPic selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ProductPic row, @Param("example") ProductPicExample example);

    int updateByExample(@Param("row") ProductPic row, @Param("example") ProductPicExample example);

    int updateByPrimaryKeySelective(ProductPic row);

    int updateByPrimaryKey(ProductPic row);
}