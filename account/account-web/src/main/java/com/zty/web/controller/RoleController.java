package com.zty.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zty.common.DO.AccountRoleRelationDO;
import com.zty.common.DO.RoleInfoDO;
import com.zty.common.service.RoleService;
import com.zty.common.service.AccountService;
import com.zty.framework.annotation.CheckToken;
import com.zty.framework.dto.ResultDTO;

/**
 * @author tianyi
 * @date 2021-04-05 19:57
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountService accountService;

    @CheckToken
    @GetMapping("/pageList")
    public ResultDTO pageList(RoleInfoDO roleInfoDO){
        int currentUID = roleInfoDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 检查当前账号是否有超管权限
        if (!accountService.checkIsAdmin(currentUID, 1)) {
            return ResultDTO.error(403, "当前账号无权操作");
        }
        // 执行业务
        try {
            Page<RoleInfoDO> pageResult = roleService.getRoleListByPage(roleInfoDO);
            return ResultDTO.success(pageResult.getResult(), pageResult.getTotal());
        } catch (ClassCastException e) {
            log.error("分页查询角色列表异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        }
    }

    @CheckToken
    @PostMapping("/setRole")
    public ResultDTO setRole(AccountRoleRelationDO relationDO){
        log.info("设置角色权限，入参:{}", JSONObject.toJSONString(relationDO));
        int currentUID = relationDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 检查当前账号是否有超管权限
        if (!accountService.checkIsAdmin(currentUID, 1)) {
            return ResultDTO.error(403, "当前账号无权操作");
        }
        // 执行业务
        boolean isOk = roleService.setRole(relationDO);
        if (isOk) {
            return ResultDTO.success();
        } else {
            return ResultDTO.error(500, "修改失败");
        }
    }
}
