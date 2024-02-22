package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.OrderItem;
import com.peaq.ec3.shopp.model.ShopCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCarMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ShopCar row);

    int insertSelective(ShopCar row);

    ShopCar selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopCar row);

    int updateByPrimaryKey(ShopCar row);

    List<ShopCar> getShopCarList(Long userId);

    int delCarBatch(@Param("ids") List<Long> ids);

    int addBatch(@Param("cars") List<ShopCar> cars);

    int updateBatch(@Param("updateCar") List<ShopCar> updateCar);

    int delCarByUser(Long userId);
}