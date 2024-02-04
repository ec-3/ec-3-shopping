package com.peaq.ec3.shopp.mapper;

import com.peaq.ec3.shopp.model.AddressManage;
import com.peaq.ec3.shopp.model.AddressManageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddressManageMapper {
    long countByExample(AddressManageExample example);

    int deleteByExample(AddressManageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AddressManage row);

    int insertSelective(AddressManage row);

    List<AddressManage> selectByExample(AddressManageExample example);

    AddressManage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") AddressManage row, @Param("example") AddressManageExample example);

    int updateByExample(@Param("row") AddressManage row, @Param("example") AddressManageExample example);

    int updateByPrimaryKeySelective(AddressManage row);

    int updateByPrimaryKey(AddressManage row);
}