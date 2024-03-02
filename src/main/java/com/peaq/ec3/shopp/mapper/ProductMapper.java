package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.Product;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Product row);

    int insertSelective(Product row);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product row);

    int updateByPrimaryKey(Product row);
}