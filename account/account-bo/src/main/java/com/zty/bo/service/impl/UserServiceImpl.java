package com.zty.bo.service.impl;

import com.zty.bo.dao.UserInfoDOMapper;
import com.zty.bo.service.UserService;
import com.zty.common.DO.UserInfoDO;
import com.zty.common.DO.example.UserInfoDOExample;
import com.zty.common.dto.UserInfoDTO;
import com.zty.framework.util.ReflectUtil;
import com.zty.framework.util.UUidUtil;
import com.zty.framework.util.md5.MD5;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * @author tianyi
 * @date 2020-04-19 15:49
 */
@Component
public class UserServiceImpl implements UserService {

    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserInfoDOMapper userInfoDOMapper;

    /**
     * 用户登录
     *
     * @param loginInfo
     * @return
     */
    @Override
    public UserInfoDTO login(UserInfoDO loginInfo) throws NoSuchAlgorithmException, IllegalAccessException, InstantiationException {
        // 对用户输入的密码进行MD5加密
        try {
            loginInfo.setPassword(MD5.encrypt(loginInfo.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            log.error("找不到加密算法", e);
            throw e;
        }
        // 检查密码正确性
        UserInfoDOExample passwordCheck = new UserInfoDOExample();
        passwordCheck.createCriteria().andPhoneEqualTo(loginInfo.getPhone()).andPasswordEqualTo(loginInfo.getPassword());
        List<UserInfoDO> passwordObj = userInfoDOMapper.selectByExample(passwordCheck);
        if (passwordObj == null){
            log.warn("用户不存在，参数={}", loginInfo);
            return null;
        }
        return (UserInfoDTO) ReflectUtil.propertyMapper(passwordObj.get(0),
                UserInfoDO.class, UserInfoDTO.class);
    }

    /**
     * 用户注册
     *
     * @param userInfoDO
     * @return
     */
    @Override
    public int register(UserInfoDO userInfoDO) throws NoSuchAlgorithmException {
        if (userInfoDO.getAccountKind() == 1) {
            // 检查手机号是否已存在
            UserInfoDOExample phoneCheck = new UserInfoDOExample();
            phoneCheck.createCriteria().andPhoneEqualTo(userInfoDO.getPhone());
            int phoneCount = userInfoDOMapper.countByExample(phoneCheck);
            if (phoneCount != 0) {
                // -1 表示手机号已存在
                return -1;
            }
            // 密码加密
            if (!StringUtils.isBlank(userInfoDO.getPassword())){
                try {
                    userInfoDO.setPassword(MD5.encrypt(userInfoDO.getPassword()));
                } catch (NoSuchAlgorithmException e) {
                    log.error("找不到加密算法", e);
                    throw e;
                }
            }else{
                log.warn("密码为空，参数={}",userInfoDO);
                return 0;
            }
            userInfoDO.setCreateTime(new Date());
            userInfoDO.setUpdateTime(new Date());
            return userInfoDOMapper.insertSelective(userInfoDO);
        }else if (userInfoDO.getAccountKind() == 2){
            // 检查openid是否已存在
            UserInfoDOExample openidCheck = new UserInfoDOExample();
            openidCheck.createCriteria().andOpenidEqualTo(userInfoDO.getOpenid());
            int openidCount = userInfoDOMapper.countByExample(openidCheck);
            if (openidCount != 0) {
                // -1 表示openid已存在
                return -1;
            }
            userInfoDO.setPhone(UUidUtil.uuid().substring(0,10));
            userInfoDO.setPassword(UUidUtil.uuid().substring(0,20));
            userInfoDO.setCreateTime(new Date());
            userInfoDO.setUpdateTime(new Date());
            return userInfoDOMapper.insertSelective(userInfoDO);
        }else{
            return -2; //-2表示账号类型不合法
        }
    }

    /**
     * 修改账号信息（包括密码）
     *
     * @param userInfoDO
     * @return
     */
    @Override
    public int update(UserInfoDO userInfoDO) throws NoSuchAlgorithmException {
        if (userInfoDO.getAccountKind() == null){
            log.warn("必须传入账号类型");
            return 0;
        }
        if (userInfoDO.getAccountKind() == 1) {
            // 如果密码非空，则加密
            if (!StringUtils.isBlank(userInfoDO.getPassword())) {
                try {
                    userInfoDO.setPassword(MD5.encrypt(userInfoDO.getPassword()));
                } catch (NoSuchAlgorithmException e) {
                    log.error("找不到加密算法", e);
                    throw e;
                }
            }
        }else if (userInfoDO.getAccountKind() == 2){

        }else{
            log.warn("账号类型非法，accountKind={}", userInfoDO.getAccountKind());
            return 0;
        }
        // 其他参数处理
        userInfoDO.setUpdateTime(new Date());

        return userInfoDOMapper.updateByPrimaryKeySelective(userInfoDO);
    }

    @Override
    public UserInfoDTO checkByOpenid(String openid) throws IllegalAccessException, InstantiationException {
        // 检查openid是否已存在
        UserInfoDOExample openidCheck = new UserInfoDOExample();
        openidCheck.createCriteria().andOpenidEqualTo(openid);
        List<UserInfoDO> result = userInfoDOMapper.selectByExample(openidCheck);
        return (UserInfoDTO) ReflectUtil.propertyMapper(result == null ? null : result.get(0),
                UserInfoDO.class,
                UserInfoDTO.class);
    }
}
