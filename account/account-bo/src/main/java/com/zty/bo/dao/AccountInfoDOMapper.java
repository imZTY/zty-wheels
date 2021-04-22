package com.zty.bo.dao;

import com.zty.common.DO.AccountInfoDO;
import com.zty.common.DO.example.AccountInfoDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountInfoDOMapper {
    int countByExample(AccountInfoDOExample example);

    int deleteByExample(AccountInfoDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountInfoDO record);

    int insertSelective(AccountInfoDO record);

    List<AccountInfoDO> selectByExample(AccountInfoDOExample example);

    AccountInfoDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountInfoDO record, @Param("example") AccountInfoDOExample example);

    int updateByExample(@Param("record") AccountInfoDO record, @Param("example") AccountInfoDOExample example);

    int updateByPrimaryKeySelective(AccountInfoDO record);

    int updateByPrimaryKey(AccountInfoDO record);

    List<AccountInfoDO> selectByRoleIds(List<Integer> roleIds);
}