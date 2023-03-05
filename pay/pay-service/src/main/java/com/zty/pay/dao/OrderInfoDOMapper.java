package com.zty.pay.dao;

import java.util.List;

import com.zty.pay.DO.OrderInfoDO;
import com.zty.pay.DO.example.OrderInfoDOExample;
import org.apache.ibatis.annotations.Param;

public interface OrderInfoDOMapper {
    int countByExample(OrderInfoDOExample example);

    int deleteByExample(OrderInfoDOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderInfoDO record);

    int insertSelective(OrderInfoDO record);

    List<OrderInfoDO> selectByExample(OrderInfoDOExample example);

    OrderInfoDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderInfoDO record, @Param("example") OrderInfoDOExample example);

    int updateByExample(@Param("record") OrderInfoDO record, @Param("example") OrderInfoDOExample example);

    int updateByPrimaryKeySelective(OrderInfoDO record);

    int updateByPrimaryKey(OrderInfoDO record);
}