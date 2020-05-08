package com.zty.bo.dao;

import com.zty.common.DO.FileInfoDO;
import com.zty.common.DO.example.FileInfoDOExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface FileInfoDOMapper {
    int countByExample(FileInfoDOExample example);

    int deleteByExample(FileInfoDOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FileInfoDO record);

    int insertSelective(FileInfoDO record);

    List<FileInfoDO> selectByExample(FileInfoDOExample example);

    FileInfoDO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FileInfoDO record, @Param("example") FileInfoDOExample example);

    int updateByExample(@Param("record") FileInfoDO record, @Param("example") FileInfoDOExample example);

    int updateByPrimaryKeySelective(FileInfoDO record);

    int updateByPrimaryKey(FileInfoDO record);
}