package com.zty.common.service;

import java.util.List;

import com.github.pagehelper.Page;

import com.zty.common.DO.AccountRoleRelationDO;
import com.zty.common.DO.RoleInfoDO;

/**
 * @author tianyi
 * @date 2021-04-05 19:59
 */
public interface RoleService {

    /**
     * 分页获取角色列表
     * @param roleInfoDO
     * @return
     */
    public Page<RoleInfoDO> getRoleListByPage(RoleInfoDO roleInfoDO);

    /**
     * 修改账号的角色
     * @param accountRoleRelationDO
     * @return
     */
    public boolean setRole(AccountRoleRelationDO accountRoleRelationDO);

    /**
     * 根据账号获取角色id
     * @param accountId
     * @return
     */
    public List<AccountRoleRelationDO> getRoleIdByAccount(int accountId);

    /**
     * 获取角色信息
     * @param roleId
     * @return
     */
    public RoleInfoDO getRoleById(Integer roleId);

    /**
     * 权限检查
     * @param roleId 角色id
     * @param rights 注解中的权限数组
     * @return 用户是否享有这些权限
     */
    public boolean checkRight(Integer roleId, String[] rights);

    int add(Integer roleId, Integer accountId);
}
