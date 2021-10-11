package com.zty.bo.dao;

import com.zty.common.DO.AccountProductRelationDO;
import com.zty.common.DO.example.AccountProductRelationDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface AccountProductRelationDOMapper {
    int countByExample(AccountProductRelationDOExample example);

    int deleteByExample(AccountProductRelationDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountProductRelationDO record);

    int insertSelective(AccountProductRelationDO record);

    List<AccountProductRelationDO> selectByExample(AccountProductRelationDOExample example);

    AccountProductRelationDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountProductRelationDO record, @Param("example") AccountProductRelationDOExample example);

    int updateByExample(@Param("record") AccountProductRelationDO record, @Param("example") AccountProductRelationDOExample example);

    int updateByPrimaryKeySelective(AccountProductRelationDO record);

    int updateByPrimaryKey(AccountProductRelationDO record);
}