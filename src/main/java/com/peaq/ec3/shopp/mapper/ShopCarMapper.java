package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.ShopCar;
import com.peaq.ec3.shopp.model.ShopCarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopCarMapper {
    long countByExample(ShopCarExample example);

    int deleteByExample(ShopCarExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopCar row);

    int insertSelective(ShopCar row);

    List<ShopCar> selectByExample(ShopCarExample example);

    ShopCar selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") ShopCar row, @Param("example") ShopCarExample example);

    int updateByExample(@Param("row") ShopCar row, @Param("example") ShopCarExample example);

    int updateByPrimaryKeySelective(ShopCar row);

    int updateByPrimaryKey(ShopCar row);
}