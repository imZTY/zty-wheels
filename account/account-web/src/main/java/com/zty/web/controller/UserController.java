package com.zty.web.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zty.common.DO.AccountRoleRelationDO;
import com.zty.common.DO.UserInfoDO;
import com.zty.common.service.AccountService;
import com.zty.common.service.RoleService;
import com.zty.common.service.UserService;
import com.zty.framework.annotation.CheckToken;
import com.zty.framework.dto.ResultDTO;

/**
 * 用户信息修改接口
 * @author tianyi
 * @date 2021-04-07 01:01
 */
@RestController
@RequestMapping("/userInfo")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountService accountService;

    @CheckToken
    @GetMapping("/getMine")
    public ResultDTO getMine(UserInfoDO userInfoDO){
        int currentUID = userInfoDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        return ResultDTO.success(userService.findUserInfo(currentUID));
    }

    @CheckToken
    @PostMapping("/updateMine")
    public ResultDTO updateMine(UserInfoDO userInfoDO){
        int currentUID = userInfoDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 只能修改自己的信息
        userInfoDO.setAccountId(currentUID);
        userInfoDO.setUpdateTime(new Date());
        boolean isOK = userService.updateUserInfo(userInfoDO);
        if (isOK) {
            // 如果是"已认证用户"，则将重新认证
            if (accountService.checkIsAdmin(currentUID, 3)) {
                AccountRoleRelationDO relationDO = new AccountRoleRelationDO();
                relationDO.setRoleId(2);
                relationDO.setUpdateTime(new Date());
                relationDO.setAccountId(currentUID);
                roleService.setRole(relationDO);
            }
            return ResultDTO.success();
        } else {
            log.error("用户信息修改失败，入参: {}", userInfoDO);
            return ResultDTO.error(500, "修改失败");
        }
    }
}
