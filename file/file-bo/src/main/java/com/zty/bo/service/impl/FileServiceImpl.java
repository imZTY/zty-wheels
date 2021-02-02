package com.zty.bo.service.impl;

import com.zty.bo.dao.FileInfoDOMapper;
import com.zty.bo.service.FileService;
import com.zty.common.DO.FileInfoDO;
import com.zty.common.DO.example.FileInfoDOExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tianyi
 * @date 2020-05-02 01:18
 */
@Service
public class FileServiceImpl implements FileService {

    Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileInfoDOMapper fileInfoDOMapper;

    @Override
    public int create(FileInfoDO fileInfoDO) {
        return fileInfoDOMapper.insertSelective(fileInfoDO);
    }

    /**
     * 获取用户的所有文件
     *
     * @param userId
     * @return
     */
    @Override
    public List<FileInfoDO> listMine(int userId) {
        FileInfoDOExample createBy = new FileInfoDOExample();
        createBy.createCriteria().andCreateByEqualTo(userId);
        return fileInfoDOMapper.selectByExample(createBy);
    }

    @Override
    @Transactional
    public FileInfoDO createAndReturnRecord(FileInfoDO fileInfoDO) {
        int count = fileInfoDOMapper.insertSelective(fileInfoDO);
        int maxId = 0;
        if (count == 0){
            return null;
        }else{
            maxId = fileInfoDOMapper.getMaxID();
        }
        FileInfoDO rt = fileInfoDOMapper.selectByPrimaryKey(maxId);
        return rt;
    }

    @Override
    public FileInfoDO findById(int id) {
        FileInfoDO rt = fileInfoDOMapper.selectByPrimaryKey(id);
        if (rt == null){
            return null;
        }
        return rt;
    }
}
