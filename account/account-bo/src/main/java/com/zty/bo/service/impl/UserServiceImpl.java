//package com.zty.bo.service.impl;
//
//import com.zty.bo.dao.AccountInfoDOMapper;
//import com.zty.bo.service.UserService;
//import com.zty.common.DO.AccountInfoDO;
//import com.zty.common.DO.example.AccountInfoDOExample;
//import com.zty.common.dto.UserInfoDTO;
//import com.zty.framework.util.ReflectUtil;
//import com.zty.framework.util.UUidUtil;
//import com.zty.framework.util.md5.MD5;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
//import java.util.List;
//
///**
// * @author tianyi
// * @date 2020-04-19 15:49
// */
//@Component
//public class UserServiceImpl implements UserService {
//
//    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
//
//    @Autowired
//    private AccountInfoDOMapper AccountInfoDOMapper;
//
//    /**
//     * 用户登录
//     *
//     * @param loginInfo
//     * @return
//     */
//    @Override
//    public UserInfoDTO login(AccountInfoDO loginInfo) throws NoSuchAlgorithmException, IllegalAccessException, InstantiationException {
//        // 对用户输入的密码进行MD5加密
//        try {
//            loginInfo.setPassword(MD5.encrypt(loginInfo.getPassword()));
//        } catch (NoSuchAlgorithmException e) {
//            log.error("找不到加密算法", e);
//            throw e;
//        }
//        // 检查密码正确性
//        AccountInfoDOExample passwordCheck = new AccountInfoDOExample();
//        passwordCheck.createCriteria().andPhoneEqualTo(loginInfo.getPhone()).andPasswordEqualTo(loginInfo.getPassword());
//        List<AccountInfoDO> passwordObj = AccountInfoDOMapper.selectByExample(passwordCheck);
//        if (passwordObj == null){
//            log.warn("用户不存在，参数={}", loginInfo);
//            return null;
//        }
//        return (UserInfoDTO) ReflectUtil.propertyMapper(passwordObj.get(0),
//                AccountInfoDO.class, UserInfoDTO.class);
//    }
//
//    /**
//     * 用户注册
//     *
//     * @param AccountInfoDO
//     * @return
//     */
//    @Override
//    public int register(AccountInfoDO AccountInfoDO) throws NoSuchAlgorithmException {
//        if (AccountInfoDO.getAccountKind() == 1) {
//            // 检查手机号是否已存在
//            AccountInfoDOExample phoneCheck = new AccountInfoDOExample();
//            phoneCheck.createCriteria().andPhoneEqualTo(AccountInfoDO.getPhone());
//            int phoneCount = AccountInfoDOMapper.countByExample(phoneCheck);
//            if (phoneCount != 0) {
//                // -1 表示手机号已存在
//                return -1;
//            }
//            // 密码加密
//            if (!StringUtils.isBlank(AccountInfoDO.getPassword())){
//                try {
//                    AccountInfoDO.setPassword(MD5.encrypt(AccountInfoDO.getPassword()));
//                } catch (NoSuchAlgorithmException e) {
//                    log.error("找不到加密算法", e);
//                    throw e;
//                }
//            }else{
//                log.warn("密码为空，参数={}",AccountInfoDO);
//                return 0;
//            }
//            AccountInfoDO.setCreateTime(new Date());
//            AccountInfoDO.setUpdateTime(new Date());
//            return AccountInfoDOMapper.insertSelective(AccountInfoDO);
//        }else if (AccountInfoDO.getAccountKind() == 2){
//            // 检查openid是否已存在
//            AccountInfoDOExample openidCheck = new AccountInfoDOExample();
//            openidCheck.createCriteria().andOpenidEqualTo(AccountInfoDO.getOpenid());
//            int openidCount = AccountInfoDOMapper.countByExample(openidCheck);
//            if (openidCount != 0) {
//                // -1 表示openid已存在
//                return -1;
//            }
//            AccountInfoDO.setPhone(UUidUtil.uuid().substring(0,10));
//            AccountInfoDO.setPassword(UUidUtil.uuid().substring(0,20));
//            AccountInfoDO.setCreateTime(new Date());
//            AccountInfoDO.setUpdateTime(new Date());
//            return AccountInfoDOMapper.insertSelective(AccountInfoDO);
//        }else{
//            return -2; //-2表示账号类型不合法
//        }
//    }
//
//    /**
//     * 修改账号信息（包括密码）
//     *
//     * @param AccountInfoDO
//     * @return
//     */
//    @Override
//    public int update(AccountInfoDO AccountInfoDO) throws NoSuchAlgorithmException {
//        if (AccountInfoDO.getAccountKind() == null){
//            log.warn("必须传入账号类型");
//            return 0;
//        }
//        if (AccountInfoDO.getAccountKind() == 1) {
//            // 如果密码非空，则加密
//            if (!StringUtils.isBlank(AccountInfoDO.getPassword())) {
//                try {
//                    AccountInfoDO.setPassword(MD5.encrypt(AccountInfoDO.getPassword()));
//                } catch (NoSuchAlgorithmException e) {
//                    log.error("找不到加密算法", e);
//                    throw e;
//                }
//            }
//        }else if (AccountInfoDO.getAccountKind() == 2){
//
//        }else{
//            log.warn("账号类型非法，accountKind={}", AccountInfoDO.getAccountKind());
//            return 0;
//        }
//        // 其他参数处理
//        AccountInfoDO.setUpdateTime(new Date());
//
//        return AccountInfoDOMapper.updateByPrimaryKeySelective(AccountInfoDO);
//    }
//
//    @Override
//    public UserInfoDTO checkByOpenid(String openid) throws IllegalAccessException, InstantiationException {
//        // 检查openid是否已存在
//        AccountInfoDOExample openidCheck = new AccountInfoDOExample();
//        openidCheck.createCriteria().andOpenidEqualTo(openid);
//        List<AccountInfoDO> result = AccountInfoDOMapper.selectByExample(openidCheck);
//        return (UserInfoDTO) ReflectUtil.propertyMapper(result == null ? null : result.get(0),
//                AccountInfoDO.class,
//                UserInfoDTO.class);
//    }
//}
