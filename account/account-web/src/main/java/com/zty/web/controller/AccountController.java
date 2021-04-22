package com.zty.web.controller;

import com.zty.bo.api.LoginCacheApi;
import com.zty.bo.service.HashService;
import com.zty.common.DO.AccountRoleRelationDO;
import com.zty.common.constant.RedisConstant;
import com.zty.common.service.AccountService;
import com.zty.common.DO.AccountInfoDO;
import com.zty.common.config.TokenConfig;
import com.zty.common.constant.AccountType;
import com.zty.common.dto.LoginInfoDTO;
import com.zty.common.service.RoleService;
import com.zty.common.service.UserService;
import com.zty.framework.annotation.CheckToken;
import com.zty.framework.dto.ResultDTO;
import com.zty.framework.util.UUidUtil;
import com.zty.web.dto.LoginResponse;

import com.github.pagehelper.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tianyi
 * @date 2020-04-19 16:22
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private TokenConfig tokenConfig;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private LoginCacheApi loginCacheApi;

    @Autowired
    private HashService hashService;

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
     * {
     *     "resultCode": 200,
     *     "resultMsg": "成功",
     *     "data": {
     *         "id": 2,
     *         "name": "曾天臆",
     *         "phone": "18320444515",
     *         "headPic": null,
     *         "sex": 0,
     *         "email": null,
     *         "province": null,
     *         "city": null,
     *         "country": null,
     *         "accountKind": 1,
     *         "openid": null,
     *         "roleId": null,
     *         "age": null,
     *         "job": null,
     *         "organization": null,
     *         "createTime": "2020-05-02T04:54:33.000+0000",
     *         "updateTime": "2020-05-02T04:54:33.000+0000"
     *     },
     *     "count": 0
     * }
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
    public ResultDTO<LoginResponse> login(AccountInfoDO accountInfoDO, HttpServletResponse response){
        // 参数校验
        if (StringUtils.isBlank(accountInfoDO.getAccount())){
            return ResultDTO.error(403, "登录账号不能为空");
        }
        if (StringUtils.isBlank(accountInfoDO.getPassword())){
            return ResultDTO.error(403, "登录密码不能为空");
        }
        // 执行业务
        try {
            LoginInfoDTO loginInfoDTO = accountService.login(accountInfoDO);
            if (loginInfoDTO == null){
                return ResultDTO.error(444, "手机号或密码错误");
            }else {
                String token = UUidUtil.uuid();
                List<AccountRoleRelationDO> relationList = roleService.getRoleIdByAccount(loginInfoDTO.getId());
                String roleId =  "2";
                String roleRights = null;
                if (CollectionUtils.isEmpty(relationList)) {
                    // 首次登录若无角色默认"2 - 未认证用户"
                    roleService.add(2, loginInfoDTO.getId());
                    loginCacheApi.setLoginAccountToken(token,
                            loginInfoDTO.getId(),
                            2);
                    roleRights = roleService.getRoleById(2).getRights();
                } else {
                    roleId = relationList.stream().map(relation -> {
                        return relation.getRoleId() + "";
                    }).collect(Collectors.joining(","));
                    loginCacheApi.setLoginAccountToken(token,
                            loginInfoDTO.getId(),
                            relationList.get(0).getRoleId());
                    roleRights = roleService.getRoleById(relationList.get(0).getRoleId()).getRights();
                }
                loginInfoDTO.setRoleId(roleId);
                if (StringUtils.isNotBlank(roleRights)) {
                    // 将角色权限放到缓存里
                    hashService.put(RedisConstant.ROLE_HASH_NAME, String.valueOf(roleId), roleRights);
                }
                Cookie cookie = new Cookie("token", token);
                cookie.setPath(tokenConfig.getPath());
                cookie.setMaxAge(tokenConfig.getExpires()); //单位秒，60*60*8=28800
                cookie.setHttpOnly(true);
                response.addCookie(cookie);
                LoginResponse rt = LoginResponse.success(token,
                        loginInfoDTO,
                        roleRights,
                        userService.findUserInfo(loginInfoDTO.getId()));
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
     * @api {post} /account/register 注册平台账号
     * @apiGroup Account
     * @apiParam {String} name 姓名/昵称【必填】
     * @apiParam {String} phone 手机号【必填】
     * @apiParam {String} password 密码【必填】
     * @apiSuccessExample Success-Request:
     * {
     *     name:zty
     * phone:18XXXXXXXX5
     * password:abc123
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
    public ResultDTO register(AccountInfoDO accountInfoDO){
        // 参数校验
        if (StringUtils.isBlank(accountInfoDO.getPhone())){
            return ResultDTO.error(403, "登录手机号不能为空");
        }
        if (StringUtils.isBlank(accountInfoDO.getPassword())){
            return ResultDTO.error(403, "登录密码不能为空");
        }
        if (accountInfoDO.getAccountType() == null) {
            // 默认手机号注册
            accountInfoDO.setAccountType(AccountType.PHONE_ACCOUNT);
        }
        if (accountInfoDO.getAccountType() != AccountType.PHONE_ACCOUNT) {
            return ResultDTO.error(403, "目前仅支持手机号注册");
        }
        // 执行业务
        try {
            accountInfoDO.setAccount(accountInfoDO.getPhone());
            accountInfoDO.setCreateBy(accountInfoDO.getCurrentUID());
            int count = accountService.register(accountInfoDO);
            if (count == -1){
                return ResultDTO.error(444, "注册失败，手机号已存在");
            }else if (count == 0){
                return ResultDTO.error(500, "注册失败，未知原因");
            }else{
                return ResultDTO.success(count);
            }
        } catch (NoSuchAlgorithmException e) {
            return ResultDTO.error(500, "密码加密时出错，请联系管理员");
        } catch (Exception e) {
            log.error("账号注册，未知异常, ", e);
            return ResultDTO.error(500, "账号注册，未知异常"+e.getMessage());
        }
    }

    /**
     * @api {post} /account/update 修改账号信息
     * @apiGroup Account
     * @apiParam {String} name 姓名/昵称【可选】
     * @apiParam {String} phone 手机号【可选】
     * @apiParam {String} password 密码【可选】
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
    @CheckToken
    @PostMapping("/update")
    public ResultDTO update(AccountInfoDO accountInfoDO){
        int currentUID = accountInfoDO.getCurrentUID();
        // 参数校验
        if (currentUID == 0){
            return ResultDTO.error(403, "用户未登陆");
        }
        // 若非修改自己，则需超管权限
        if (accountInfoDO.getId() != null &&
                accountInfoDO.getId() != currentUID) {
            // 检查当前账号是否有超管权限
            if (!accountService.checkIsAdmin(currentUID, 1)) {
                return ResultDTO.error(403, "当前账号无权操作");
            }
        } else {
            accountInfoDO.setId(accountInfoDO.getCurrentUID());
        }
        // 执行业务
        try {
            accountInfoDO.setAccountType(AccountType.PHONE_ACCOUNT);
            int count = accountService.update(accountInfoDO);
            if (count == 0){
                return ResultDTO.error(500, "修改失败，未知原因");
            }else{
                return ResultDTO.success(count);
            }
        } catch (NoSuchAlgorithmException e) {
            return ResultDTO.error(500, "修改失败，密码加密时出错，请联系管理员");
        }
    }

    @CheckToken
    @GetMapping("/pageList")
    public ResultDTO pageList(AccountInfoDO accountInfoDO){
        int currentUID = accountInfoDO.getCurrentUID();
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
            Page<AccountInfoDO> pageResult = accountService.getAccountListByPage(accountInfoDO);
            List<LoginInfoDTO> resultData = accountService.parseAccountListToLoginInfoList(pageResult.getResult());
            return ResultDTO.success(resultData, pageResult.getTotal());
        } catch (ClassCastException e) {
            log.error("分页查询账号列表异常, ", e);
            return ResultDTO.error(500, "类型转换异常"+e.getMessage());
        }
    }

}
