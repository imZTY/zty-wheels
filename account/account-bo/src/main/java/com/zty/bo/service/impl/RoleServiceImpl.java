package com.zty.bo.service.impl;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.zty.bo.dao.AccountRoleRelationDOMapper;
import com.zty.bo.dao.RoleInfoDOMapper;
import com.zty.bo.service.HashService;
import com.zty.common.DO.AccountRoleRelationDO;
import com.zty.common.DO.RoleInfoDO;
import com.zty.common.DO.example.AccountRoleRelationDOExample;
import com.zty.common.DO.example.RoleInfoDOExample;
import com.zty.framework.constant.Disabled;
import com.zty.common.constant.RedisConstant;
import com.zty.common.constant.SpliterConstant;
import com.zty.common.service.RoleService;

/**
 * @author tianyi
 * @date 2021-04-05 20:18
 */
@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleInfoDOMapper roleInfoDOMapper;

    @Autowired
    private AccountRoleRelationDOMapper accountRoleRelationDOMapper;

    @Autowired
    private HashService hashService;

    /**
     * 分页获取角色列表
     *
     * @param roleInfoDO
     * @return
     */
    @Override
    public Page<RoleInfoDO> getRoleListByPage(RoleInfoDO roleInfoDO) {
        RoleInfoDOExample example = new RoleInfoDOExample();
        PageHelper.startPage(roleInfoDO.getPage(), roleInfoDO.getRows());
        return (Page<RoleInfoDO>) roleInfoDOMapper.selectByExample(example);
    }

    /**
     * 修改账号的角色
     * 注意鉴权
     * @param accountRoleRelationDO
     * @return
     */
    @Override
    public boolean setRole(AccountRoleRelationDO accountRoleRelationDO) {
        try {
            setRoleTransation(accountRoleRelationDO);
            return true;
        } catch (Exception e) {
            log.error("设置角色权限异常, ", e);
            return false;
        }
    }

    /**
     *
     * @param accountRoleRelationDO
     */
    @Transactional(rollbackFor = Exception.class)
    void setRoleTransation(AccountRoleRelationDO accountRoleRelationDO) {
        int accountId = accountRoleRelationDO.getAccountId();
        int roleId = accountRoleRelationDO.getRoleId();
        // 将所有已存在的设为不可用
        setAllToDisabled(accountId);
        // 再查询符合条件的
        AccountRoleRelationDOExample example = new AccountRoleRelationDOExample();
        example.createCriteria()
                .andAccountIdEqualTo(accountId)
                .andRoleIdEqualTo(roleId);
        List<AccountRoleRelationDO> existList = accountRoleRelationDOMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(existList)) {
            // 已存在记录
            AccountRoleRelationDO exist = existList.get(0);
            exist.setDisabled(Disabled.FALSE);
            exist.setUpdateTime(new Date());
            accountRoleRelationDOMapper.updateByPrimaryKeySelective(exist);
        } else {
            // 不存在记录
            AccountRoleRelationDO newRelation = new AccountRoleRelationDO();
            newRelation.setAccountId(accountId);
            newRelation.setRoleId(roleId);
            newRelation.setDisabled(Disabled.FALSE);
            newRelation.setCreateTime(new Date());
            accountRoleRelationDOMapper.insertSelective(newRelation);
        }
    }

    private void setAllToDisabled(int accountId) {
        AccountRoleRelationDOExample example = new AccountRoleRelationDOExample();
        example.createCriteria().andAccountIdEqualTo(accountId);
        AccountRoleRelationDO accountRoleRelationDO = new AccountRoleRelationDO();
        accountRoleRelationDO.setDisabled(Disabled.TRUE);
        accountRoleRelationDO.setUpdateTime(new Date());
        accountRoleRelationDOMapper.updateByExampleSelective(accountRoleRelationDO, example);
    }

    /**
     * 根据账号获取角色id
     *
     * @param accountId
     * @return
     */
    @Override
    public List<AccountRoleRelationDO> getRoleIdByAccount(int accountId) {
        AccountRoleRelationDOExample example = new AccountRoleRelationDOExample();
        example.createCriteria()
                .andAccountIdEqualTo(accountId)
                .andDisabledEqualTo(Disabled.FALSE);
        try {
            List<AccountRoleRelationDO> existList = accountRoleRelationDOMapper.selectByExample(example);
            return existList;
        } catch (Exception e) {
            log.error("获取账号{}的角色异常, ", accountId, e);
        }
        return null;
    }

    /**
     * 获取角色信息
     *
     * @param roleId
     * @return
     */
    @Override
    public RoleInfoDO getRoleById(Integer roleId) {
        return roleInfoDOMapper.selectByPrimaryKey(roleId);
    }

    /**
     * 权限检查
     *
     * @param roleId 角色id
     * @param rights 注解中的权限数组
     * @return 用户是否享有这些权限
     */
    @Override
    public boolean checkRight(Integer roleId, String[] rights) {
        boolean rt = false;
        if (roleId == 0) {
            log.warn("此用户尚未拥有权限, roleId={}", roleId);
            return false;
        }
        try{
            // 从 K,V 里获取 权限
            String roleRight = hashService.get(RedisConstant.ROLE_HASH_NAME, String.valueOf(roleId));

            if(StringUtils.isBlank(roleRight)) {
                // 若Redis缓存里面没有，则查DB
                roleRight = roleInfoDOMapper.selectByPrimaryKey(roleId).getRights();
                // 更新缓存内容 - (登录时也会更新)
                hashService.put(RedisConstant.ROLE_HASH_NAME, String.valueOf(roleId), roleRight);
            }else{
                // do nothing here
            }
            String[] allRights = roleRight.split("["+SpliterConstant.ACCOUNT_AND_ROLE+"]");
            int i = 0;
            for (; i < rights.length; i++){
                String right = rights[i];  //要检测的权限
                boolean test = false;
                for (String has : allRights){  //此角色的所有权限
                    if (right.equals(has)){
                        test = true;
                        break;  //如果含有则退出，检查下一个权限
                    }
                }
                if (!test){  //如果没有，则说明检测失败，缺少该权限
                    break;  // 必须含有全部 rights 才能通过检验
                }
            }
            if (i == rights.length){  //说明所有权限都检查完了且都可用
                rt = true;
            }
        }catch (Exception e){
            log.error("CheckRight() Exception: ",e);
            return false;
        }

        return rt;
    }

    @Override
    public int add(Integer roleId, Integer accountId) {
        AccountRoleRelationDO newRelation = new AccountRoleRelationDO();
        newRelation.setRoleId(roleId);
        newRelation.setAccountId(accountId);
        newRelation.setCreateTime(new Date());
        newRelation.setDisabled((byte) 0);
        return accountRoleRelationDOMapper.insertSelective(newRelation);
    }
}
