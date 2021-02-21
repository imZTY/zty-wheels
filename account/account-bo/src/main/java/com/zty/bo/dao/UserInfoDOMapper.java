package com.zty.bo.dao;

import com.zty.common.DO.UserInfoDO;
import com.zty.common.DO.example.UserInfoDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoDOMapper {
    int countByExample(UserInfoDOExample example);

    int deleteByExample(UserInfoDOExample example);

    int deleteByPrimaryKey(Integer accountId);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    List<UserInfoDO> selectByExample(UserInfoDOExample example);

    UserInfoDO selectByPrimaryKey(Integer accountId);

    int updateByExampleSelective(@Param("record") UserInfoDO record, @Param("example") UserInfoDOExample example);

    int updateByExample(@Param("record") UserInfoDO record, @Param("example") UserInfoDOExample example);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);
}