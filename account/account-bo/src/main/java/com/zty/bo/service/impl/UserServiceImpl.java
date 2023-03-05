package com.zty.bo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;

import com.zty.bo.dao.UserInfoDOMapper;
import com.zty.common.DO.UserInfoDO;
import com.zty.common.service.UserService;

/**
 * @author tianyi
 * @date 2021-04-05 21:13
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoDOMapper userInfoDOMapper;

    /**
     * 获取账号的用户资料
     *
     * @param accountId
     * @return
     */
    @Override
    public UserInfoDO findUserInfo(Integer accountId) {
        return userInfoDOMapper.selectByPrimaryKey(accountId);
    }

    /**
     * 修改用户信息
     *
     * @param userInfoDO
     * @return
     */
    @Override
    public boolean updateUserInfo(UserInfoDO userInfoDO) {
        log.info("修改用户信息:{}", userInfoDO);
        int rows = userInfoDOMapper.updateByPrimaryKeySelective(userInfoDO);
        log.error("影响条数：{}", rows);
        return rows == 1;
    }
}
