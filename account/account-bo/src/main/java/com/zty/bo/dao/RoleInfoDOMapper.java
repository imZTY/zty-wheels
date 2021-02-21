package com.zty.bo.dao;

import com.zty.common.DO.RoleInfoDO;
import com.zty.common.DO.example.RoleInfoDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RoleInfoDOMapper {
    int countByExample(RoleInfoDOExample example);

    int deleteByExample(RoleInfoDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleInfoDO record);

    int insertSelective(RoleInfoDO record);

    List<RoleInfoDO> selectByExample(RoleInfoDOExample example);

    RoleInfoDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RoleInfoDO record, @Param("example") RoleInfoDOExample example);

    int updateByExample(@Param("record") RoleInfoDO record, @Param("example") RoleInfoDOExample example);

    int updateByPrimaryKeySelective(RoleInfoDO record);

    int updateByPrimaryKey(RoleInfoDO record);
}