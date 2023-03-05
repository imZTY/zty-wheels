package com.zty.common.service;

import com.zty.common.DO.AccountInfoDO;
import com.zty.common.DO.RoleInfoDO;
import com.zty.common.DO.UserInfoDO;
import com.zty.common.dto.LoginInfoDTO;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * @author tianyi
 * @date 2020-04-19 15:44
 */
public interface AccountService {

    public AccountInfoDO findById(Integer id);

    /**
     * 用户登录
     * @param loginInfo
     * @return
     */
    public LoginInfoDTO login(AccountInfoDO loginInfo) throws NoSuchAlgorithmException, IllegalAccessException, InstantiationException;

    /**
     * 用户注册
     * @param AccountInfoDO
     * @return
     */
    public int register(AccountInfoDO AccountInfoDO) throws NoSuchAlgorithmException;

    /**
     * 修改账号信息（包括密码）
     * @param AccountInfoDO 传进来的密码要先完成MD5加密
     * @return
     */
    public int update(AccountInfoDO AccountInfoDO) throws NoSuchAlgorithmException;

    public LoginInfoDTO checkByOpenid(String openid) throws IllegalAccessException, InstantiationException;

    /**
     * 检查用户是否拥有超管权限
     * @param userId
     * @return
     */
    public boolean checkIsAdmin(int userId, Integer... roleIds);

    /**
     * 分页获取账号列表
     * @param AccountInfoDO
     * @return
     */
    public Page<AccountInfoDO> getAccountListByPage(AccountInfoDO AccountInfoDO);

    /**
     * 根绝角色ID，分页获取账号列表
     * @param AccountInfoDO
     * @return
     */
    public Page<AccountInfoDO> getAccountPageByRoleId(AccountInfoDO AccountInfoDO, List<Integer> roleIds);

    /**
     * 将账号列表转换成脱敏后的用户信息（含角色）
     * @param accountInfoDOList
     * @return
     */
    List<LoginInfoDTO> parseAccountListToLoginInfoList(List<AccountInfoDO> accountInfoDOList);
}
