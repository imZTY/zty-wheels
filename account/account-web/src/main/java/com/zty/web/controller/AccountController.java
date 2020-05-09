package com.zty.web.controller;

import com.zty.bo.api.LoginCacheApi;
import com.zty.bo.service.UserService;
import com.zty.common.DO.UserInfoDO;
import com.zty.common.dto.UserInfoDTO;
import com.zty.framework.annotation.CheckToken;
import com.zty.framework.dto.ResultDTO;
import com.zty.framework.util.UUidUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

/**
 * @author tianyi
 * @date 2020-04-19 16:22
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LoginCacheApi loginCacheApi;

    /**
     * @apiDefine Account 账号
     */

    /**
     *  @apiDefine ResultDTO
     *  @apiSuccess {Integer} resultCode 响应结果
     *  @apiSuccess {String} resultMsg 结果描述
     *  @apiSuccess {Object} data 数据主体
     *  @apiSuccess {Integer} count 总数据量
     */

    /**
     * @api {post} /account/login 账号登录
     * @apiGroup Account
     * @apiParam {String} phone 手机号【必填】
     * @apiParam {String} password 密码【必填】
     * @apiSuccessExample Success-Request:
     * {
     *     phone:18320444515
     * password:abc123
     * }
     * @apiUse ResultDTO
     * @apiSuccessExample Success-Response:
     * {}
     * @apiErrorExample Error-Respinse-有空:
     * {
     *     "resultCode": 403,
     *     "resultMsg": "登录密码不能为空",
     *     "data": null,
     *     "count": 0
     * }
     * @apiErrorExample Error-Respinse-错误:
     * {
     *     "resultCode": 444,
     *     "resultMsg": "手机号或密码错误",
     *     "data": null,
     *     "count": 0
     * }
     * @apiErrorExample Error-Respinse-异常:
     * {
     *     "resultCode": 500,
     *     "resultMsg": "密码加密时出错，请联系管理员",
     *     "data": null,
     *     "count": 0
     * }
     */
    @PostMapping("/login")
    public ResultDTO<UserInfoDTO> login(UserInfoDO userInfoDO){
        // 参数校验
        if (StringUtils.isBlank(userInfoDO.getPhone())){
            return ResultDTO.error(403, "登录手机号不能为空");
        }
        if (StringUtils.isBlank(userInfoDO.getPassword())){
            return ResultDTO.error(403, "登录密码不能为空");
        }
        // 执行业务
        try {
            UserInfoDTO rt = userService.login(userInfoDO);
            if (rt == null){
                return ResultDTO.error(444, "手机号或密码错误");
            }else {
                loginCacheApi.setTokenAndUserId(UUidUtil.uuid(), rt.getId());
                return ResultDTO.success(rt);
            }
        } catch (NoSuchAlgorithmException e) {
            return ResultDTO.error(500, "密码加密时出错，请联系管理员");
        } catch (IllegalAccessException e) {
            log.error("反射脱敏时，字段访问非法", e);
            return ResultDTO.error(500, "反射脱敏时，字段访问非法");
        } catch (InstantiationException e) {
            log.error("反射脱敏时，实例化失败", e);
            return ResultDTO.error(500, "反射脱敏时，实例化失败");
        }
    }

    /**
     * @api {post} /account/register 注册账号
     * @apiGroup Account
     * @apiParam {String} name 姓名/昵称【必填】
     * @apiParam {String} phone 手机号【必填】
     * @apiParam {String} password 密码【必填】
     * @apiParam {float} iofAge 定向年龄
     * @apiParam {String} organization 所属组织/机构
     * @apiSuccessExample Success-Request:
     * {
     *     name:zty
     * phone:18XXXXXXXX5
     * password:abc123
     * organization:广州中医药大学定向越野协会
     * }
     * @apiUse ResultDTO
     * @apiSuccessExample Success-Response:
     * {
     *     "resultCode": 200,
     *     "resultMsg": "成功",
     *     "data": 1,
     *     "count": 0
     * }
     * @apiErrorExample Error-Respinse-有空值:
     * {
     *     "resultCode": 403,
     *     "resultMsg": "登录手机号不能为空",
     *     "data": null,
     *     "count": 0
     * }
     * @apiErrorExample Error-Respinse-已存在:
     * {
     *     "resultCode": 444,
     *     "resultMsg": "注册失败，手机号已存在",
     *     "data": null,
     *     "count": 0
     * }
     * @apiErrorExample Error-Respinse-未知原因:
     * {
     *     "resultCode": 500,
     *     "resultMsg": "注册失败，未知原因",
     *     "data": null,
     *     "count": 0
     * }
     */
    @PostMapping("/register")
    public ResultDTO register(UserInfoDO userInfoDO){
        // 参数校验
        if (StringUtils.isBlank(userInfoDO.getPhone())){
            return ResultDTO.error(403, "登录手机号不能为空");
        }
        if (StringUtils.isBlank(userInfoDO.getPassword())){
            return ResultDTO.error(403, "登录密码不能为空");
        }
        // 执行业务
        try {
            int count = userService.register(userInfoDO);
            if (count == -1){
                return ResultDTO.error(444, "注册失败，手机号已存在");
            }else if (count == 0){
                return ResultDTO.error(500, "注册失败，未知原因");
            }else{
                return ResultDTO.success(count);
            }
        } catch (NoSuchAlgorithmException e) {
            return ResultDTO.error(500, "密码加密时出错，请联系管理员");
        }
    }

    @CheckToken
    /**
     * @api {post} /account/update 修改账号信息
     * @apiGroup Account
     * @apiParam {int} id 用户id【必填】
     * @apiParam {String} name 姓名/昵称【可选】
     * @apiParam {String} phone 手机号【可选】
     * @apiParam {String} password 密码【可选】
     * @apiParam {float} iofAge 定向年龄【可选】
     * @apiParam {String} organization 所属组织/机构【可选】
     * @apiSuccessExample Success-Request:
     * {
     *     id:1
     *     password:112233ab
     * }
     * @apiUse ResultDTO
     * @apiSuccessExample Success-Response:
     * {
     *     "resultCode": 200,
     *     "resultMsg": "成功",
     *     "data": 1,
     *     "count": 0
     * }
     * @apiErrorExample Error-Respinse-有空:
     * {
     *     "resultCode": 403,
     *     "resultMsg": "用户id不可为空或为0",
     *      "data": null,
     *      "count": 0
     * }
     * @apiErrorExample Error-Respinse:
     * {
     *     "resultCode": 500,
     *     "resultMsg": "修改失败，密码加密时出错，请联系管理员",
     *     "data": null,
     *     "count": 0
     * }
     * @apiErrorExample Error-Respinse:
     * {
     *     "resultCode": 500,
     *     "resultMsg": "修改失败，未知原因",
     *     "data": null,
     *     "count": 0
     * }
     */
    @PostMapping("/update")
    public ResultDTO update(UserInfoDO userInfoDO){
        // 参数校验
        if (userInfoDO.getCurrentUID() == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        userInfoDO.setId(userInfoDO.getCurrentUID());
        if (userInfoDO.getId() == null || userInfoDO.getId() == 0){
            return ResultDTO.error(403, "用户id不可为空或为0");
        }
        // 执行业务
        try {
            int count = userService.update(userInfoDO);
            if (count == 0){
                return ResultDTO.error(500, "修改失败，未知原因");
            }else{
                return ResultDTO.success(count);
            }
        } catch (NoSuchAlgorithmException e) {
            return ResultDTO.error(500, "修改失败，密码加密时出错，请联系管理员");
        }
    }
}
