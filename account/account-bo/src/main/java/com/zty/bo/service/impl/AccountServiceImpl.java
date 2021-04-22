package com.zty.bo.service.impl;

import com.zty.bo.dao.AccountInfoDOMapper;
import com.zty.bo.dao.AccountRoleRelationDOMapper;
import com.zty.bo.dao.UserInfoDOMapper;
import com.zty.common.DO.AccountRoleRelationDO;
import com.zty.common.DO.UserInfoDO;
import com.zty.common.DO.example.AccountRoleRelationDOExample;
import com.zty.framework.constant.Disabled;
import com.zty.common.service.AccountService;
import com.zty.common.DO.AccountInfoDO;
import com.zty.common.DO.example.AccountInfoDOExample;
import com.zty.common.constant.AccountType;
import com.zty.common.dto.LoginInfoDTO;
import com.zty.framework.util.ReflectUtil;
import com.zty.framework.util.UUidUtil;
import com.zty.framework.util.md5.MD5;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author tianyi
 * @date 2020-04-19 15:49
 */
@Component
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountInfoDOMapper accountInfoDOMapper;

    @Autowired
    private AccountRoleRelationDOMapper accountRoleRelationDOMapper;

    @Autowired
    private UserInfoDOMapper userInfoDOMapper;

    @Override
    public AccountInfoDO findById(Integer id) {
        return accountInfoDOMapper.selectByPrimaryKey(id);
    }

    /**
     * 用户登录
     *
     * @param loginInfo
     * @return
     */
    @Override
    public LoginInfoDTO login(AccountInfoDO loginInfo) throws NoSuchAlgorithmException, IllegalAccessException, InstantiationException {
        // 对用户输入的密码进行MD5加密
        try {
            loginInfo.setPassword(MD5.encrypt(loginInfo.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            log.error("找不到加密算法", e);
            throw e;
        }
        // 检查密码正确性
        AccountInfoDOExample passwordCheck = new AccountInfoDOExample();
        passwordCheck.createCriteria().andAccountEqualTo(loginInfo.getAccount()).andPasswordEqualTo(loginInfo.getPassword());
        List<AccountInfoDO> checkResultList = accountInfoDOMapper.selectByExample(passwordCheck);
        if (CollectionUtils.isEmpty(checkResultList)){
            log.warn("用户不存在或密码错误，参数={}", loginInfo);
            return null;
        }
        return (LoginInfoDTO) ReflectUtil.propertyMapper(checkResultList.get(0),
                AccountInfoDO.class, LoginInfoDTO.class);
    }

    /**
     * 用户注册
     *
     * @param AccountInfoDO
     * @return
     */
    @Override
    public int register(AccountInfoDO AccountInfoDO) throws NoSuchAlgorithmException {
        if (AccountInfoDO.getAccountType() == AccountType.PHONE_ACCOUNT) {
            // 检查手机号是否已存在
            AccountInfoDOExample phoneCheck = new AccountInfoDOExample();
            phoneCheck.createCriteria()
                    .andAccountEqualTo(AccountInfoDO.getPhone());
            int phoneCount = accountInfoDOMapper.countByExample(phoneCheck);
            if (phoneCount != 0) {
                // -1 表示手机号已存在
                return -1;
            }
            // 密码加密
            if (!StringUtils.isBlank(AccountInfoDO.getPassword())){
                try {
                    AccountInfoDO.setPassword(MD5.encrypt(AccountInfoDO.getPassword()));
                } catch (NoSuchAlgorithmException e) {
                    log.error("找不到加密算法", e);
                    throw e;
                }
            }else{
                log.warn("密码为空，参数={}",AccountInfoDO);
                return 0;
            }
            AccountInfoDO.setCreateTime(new Date());
            AccountInfoDO.setUpdateTime(new Date());
            int count = accountInfoDOMapper.insertSelective(AccountInfoDO);
            UserInfoDO userInfoDO = new UserInfoDO();
            userInfoDO.setAccountId(accountInfoDOMapper.selectByExample(phoneCheck).get(0).getId());
            userInfoDOMapper.insertSelective(userInfoDO);
            return count;
        }else if (AccountInfoDO.getAccountType() == AccountType.WX_ACCOUNT){
            // 检查openid是否已存在
            AccountInfoDOExample openidCheck = new AccountInfoDOExample();
            openidCheck.createCriteria().andOpenidEqualTo(AccountInfoDO.getOpenid());
            int openidCount = accountInfoDOMapper.countByExample(openidCheck);
            if (openidCount != 0) {
                // -1 表示openid已存在
                return -1;
            }
            AccountInfoDO.setPhone(UUidUtil.uuid().substring(0,10));
            AccountInfoDO.setPassword(UUidUtil.uuid().substring(0,20));
            AccountInfoDO.setCreateTime(new Date());
            AccountInfoDO.setUpdateTime(new Date());
            return accountInfoDOMapper.insertSelective(AccountInfoDO);
        }else{
            return -2; //-2表示账号类型不合法
        }
    }

    /**
     * 修改账号信息（包括密码）
     *
     * @param AccountInfoDO
     * @return
     */
    @Override
    public int update(AccountInfoDO AccountInfoDO) throws NoSuchAlgorithmException {
        if (AccountInfoDO.getAccountType() == null){
            log.warn("必须传入账号类型");
            return 0;
        }
        if (AccountInfoDO.getAccountType() == AccountType.PHONE_ACCOUNT) {
            // 如果密码非空，则加密
            if (!StringUtils.isBlank(AccountInfoDO.getPassword())) {
                try {
                    AccountInfoDO.setPassword(MD5.encrypt(AccountInfoDO.getPassword()));
                } catch (NoSuchAlgorithmException e) {
                    log.error("找不到加密算法", e);
                    throw e;
                }
            }
        } else {
            log.warn("账号类型非法，accountKind={}", AccountInfoDO.getAccountType());
            return 0;
        }
        // 其他参数处理
        AccountInfoDO.setUpdateTime(new Date());

        return accountInfoDOMapper.updateByPrimaryKeySelective(AccountInfoDO);
    }

    @Override
    public LoginInfoDTO checkByOpenid(String openid) throws IllegalAccessException, InstantiationException {
        // 检查openid是否已存在
        AccountInfoDOExample openidCheck = new AccountInfoDOExample();
        openidCheck.createCriteria().andOpenidEqualTo(openid);
        List<AccountInfoDO> result = accountInfoDOMapper.selectByExample(openidCheck);
        return (LoginInfoDTO) ReflectUtil.propertyMapper(result == null ? null : result.get(0),
                AccountInfoDO.class,
                LoginInfoDTO.class);
    }

    /**
     * 检查用户是否拥有超管权限
     *
     * @param userId
     * @return
     */
    @Override
    public boolean checkIsAdmin(int userId, Integer... roleIds) {
        AccountRoleRelationDOExample example = new AccountRoleRelationDOExample();
        example.createCriteria()
                .andAccountIdEqualTo(userId)
                .andRoleIdIn(Arrays.asList(roleIds))
                .andDisabledEqualTo(Disabled.FALSE);
        int count = accountRoleRelationDOMapper.countByExample(example);
        return count != 0;
    }

    /**
     * 分页获取账号列表
     * 未脱敏，注意鉴权
     * @param AccountInfoDO
     * @return
     */
    @Override
    public Page<AccountInfoDO> getAccountListByPage(AccountInfoDO AccountInfoDO) {
        AccountInfoDOExample example = new AccountInfoDOExample();
        PageHelper.startPage(AccountInfoDO.getPage(), AccountInfoDO.getRows());
        return  (Page<AccountInfoDO>) accountInfoDOMapper.selectByExample(example);
    }

    /**
     * 根绝角色ID，分页获取账号列表
     * @param accountInfoDO
     * @return
     */
    @Override
    public Page<AccountInfoDO> getAccountPageByRoleId(AccountInfoDO accountInfoDO, List<Integer> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            throw new IllegalArgumentException("roleIds不可为空");
        }
        PageHelper.startPage(accountInfoDO.getPage(), accountInfoDO.getRows());
        return  (Page<AccountInfoDO>) accountInfoDOMapper.selectByRoleIds(roleIds);
    }


    /**
     * 将账号列表转换成脱敏后的用户信息（含角色）
     * @param accountInfoDOList
     * @return
     */
    @Override
    public List<LoginInfoDTO> parseAccountListToLoginInfoList(List<AccountInfoDO> accountInfoDOList) {
        AccountRoleRelationDOExample example = new AccountRoleRelationDOExample();
        example.createCriteria()
                .andAccountIdIn(accountInfoDOList.stream()
                        .map(AccountInfoDO::getId)
                        .collect(Collectors.toList()))
                .andDisabledEqualTo(Disabled.FALSE);
        try {
            Map<Integer, String> roleIdMap = new HashMap<>();
            List<AccountRoleRelationDO> existList = accountRoleRelationDOMapper.selectByExample(example);
            existList.forEach(relation -> {
                roleIdMap.put(relation.getAccountId(), String.valueOf(relation.getRoleId()));
            });
            return accountInfoDOList.stream().map(accountInfoDO -> {
                try {
                    LoginInfoDTO loginInfoDTO = (LoginInfoDTO) ReflectUtil.propertyMapper(accountInfoDO,
                            AccountInfoDO.class, LoginInfoDTO.class);
                    if (roleIdMap.containsKey(loginInfoDTO.getId())) {
                        loginInfoDTO.setRoleId(roleIdMap.get(loginInfoDTO.getId()));
                    }
                    return loginInfoDTO;
                } catch (InstantiationException e) {
                    log.error("InstantiationException ", e);
                } catch (IllegalAccessException e) {
                    log.error("IllegalAccessException ", e);
                }
                throw new ClassCastException("账号信息脱敏转换异常");
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("获取账号的角色列表异常, ", e);
        }
        throw new ClassCastException("账号获取角色并脱敏时异常");
    }
}
