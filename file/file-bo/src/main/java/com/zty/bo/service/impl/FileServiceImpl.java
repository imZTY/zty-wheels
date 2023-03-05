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

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

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
    public List<FileInfoDO> listMine(int userId, List<Byte> fileKindList) {
        FileInfoDOExample example = new FileInfoDOExample();
        example.createCriteria()
                .andCreateByEqualTo(userId)
                .andFileKindIn(fileKindList);
        return fileInfoDOMapper.selectByExample(example);
    }

    @Override
    @Transactional
    public int createAndReturnRows(FileInfoDO fileInfoDO) {
        int count = fileInfoDOMapper.insertSelective(fileInfoDO);
        log.info("插入文件 影响行数：{}", count);
        return count;
    }

    @Override
    public FileInfoDO findById(int id) {
        FileInfoDO rt = fileInfoDOMapper.selectByPrimaryKey(id);
        if (rt == null){
            return null;
        }
        return rt;
    }

    @Override
    public int update(FileInfoDO fileInfoDO) {
        return fileInfoDOMapper.updateByPrimaryKeySelective(fileInfoDO);
    }

    @Override
    public List<FileInfoDO> findByFileKind(int userId, byte fileKind) {
        FileInfoDOExample createBy = new FileInfoDOExample();
        createBy.createCriteria()
                .andCreateByEqualTo(userId)
                .andFileKindEqualTo(fileKind);
        return fileInfoDOMapper.selectByExample(createBy);
    }
}
