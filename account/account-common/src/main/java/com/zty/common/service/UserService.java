package com.zty.common.service;

import com.zty.common.DO.AccountRoleRelationDO;
import com.zty.common.DO.UserInfoDO;

/**
 * @author tianyi
 * @date 2021-04-05 21:12
 */
public interface UserService {

    /**
     * 获取账号的用户资料
     * @param accountId
     * @return
     */
    UserInfoDO findUserInfo(Integer accountId);

    /**
     * 修改用户信息
     * @param userInfoDO
     * @return
     */
    public boolean updateUserInfo(UserInfoDO userInfoDO);
}
