package com.zty.bo.service;

import com.zty.common.DO.FileInfoDO;

import java.util.List;

/**
 * @author tianyi
 * @date 2020-05-01 19:15
 */
public interface FileService {

    public int create(FileInfoDO fileInfoDO);

    /**
     * 获取用户的所有文件
     * @param userId
     * @return
     */
    public List<FileInfoDO> listMine(int userId, List<Byte> fileKindList);

    public int createAndReturnRows(FileInfoDO fileInfoDO);

    public FileInfoDO findById(int id);

    int update(FileInfoDO fileInfoDO);

    List<FileInfoDO> findByFileKind(int userId, byte fileKind);
}
