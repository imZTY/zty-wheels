package com.zty.bo.dao;

import com.zty.common.DO.AccountRoleRelationDO;
import com.zty.common.DO.example.AccountRoleRelationDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountRoleRelationDOMapper {
    int countByExample(AccountRoleRelationDOExample example);

    int deleteByExample(AccountRoleRelationDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountRoleRelationDO record);

    int insertSelective(AccountRoleRelationDO record);

    List<AccountRoleRelationDO> selectByExample(AccountRoleRelationDOExample example);

    AccountRoleRelationDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountRoleRelationDO record, @Param("example") AccountRoleRelationDOExample example);

    int updateByExample(@Param("record") AccountRoleRelationDO record, @Param("example") AccountRoleRelationDOExample example);

    int updateByPrimaryKeySelective(AccountRoleRelationDO record);

    int updateByPrimaryKey(AccountRoleRelationDO record);
}