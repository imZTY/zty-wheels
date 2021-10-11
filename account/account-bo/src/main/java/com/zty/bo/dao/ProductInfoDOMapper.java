package com.zty.bo.dao;

import com.zty.common.DO.ProductInfoDO;
import com.zty.common.DO.example.ProductInfoDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface ProductInfoDOMapper {
    int countByExample(ProductInfoDOExample example);

    int deleteByExample(ProductInfoDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfoDO record);

    int insertSelective(ProductInfoDO record);

    List<ProductInfoDO> selectByExample(ProductInfoDOExample example);

    ProductInfoDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProductInfoDO record, @Param("example") ProductInfoDOExample example);

    int updateByExample(@Param("record") ProductInfoDO record, @Param("example") ProductInfoDOExample example);

    int updateByPrimaryKeySelective(ProductInfoDO record);

    int updateByPrimaryKey(ProductInfoDO record);
}