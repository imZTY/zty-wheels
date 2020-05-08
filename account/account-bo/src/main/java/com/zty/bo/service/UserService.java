package com.zty.bo.service;

import com.zty.common.DO.UserInfoDO;
import com.zty.common.dto.UserInfoDTO;

import java.security.NoSuchAlgorithmException;

/**
 * @author tianyi
 * @date 2020-04-19 15:44
 */
public interface UserService {

    /**
     * 用户登录
     * @param loginInfo
     * @return
     */
    public UserInfoDTO login(UserInfoDO loginInfo) throws NoSuchAlgorithmException, IllegalAccessException, InstantiationException;

    /**
     * 用户注册
     * @param userInfoDO
     * @return
     */
    public int register(UserInfoDO userInfoDO) throws NoSuchAlgorithmException;

    /**
     * 修改账号信息（包括密码）
     * @param userInfoDO 传进来的密码要先完成MD5加密
     * @return
     */
    public int update(UserInfoDO userInfoDO) throws NoSuchAlgorithmException;

    public UserInfoDTO checkByOpenid(String openid) throws IllegalAccessException, InstantiationException;
}
