package com.zty.web.dto;

import com.zty.common.DO.UserInfoDO;
import com.zty.common.dto.LoginInfoDTO;

/**
 * @author tianyi
 * @date 2019-07-09 11:27
 */
public class LoginResponse {

    private boolean isSuccess;

    private String message;

    private String rights;

    /**
     * 脱敏后的登录账号信息
     */
    private LoginInfoDTO loginAccountInfo;

    /**
     * 用户资料
     */
    private UserInfoDO userInfo;

    public static LoginResponse success(String token, LoginInfoDTO loginAccountInfo, String rights, UserInfoDO userInfo){
        LoginResponse rt = new LoginResponse();
        rt.setSuccess(true);
        rt.setMessage(token);
        rt.setLoginAccountInfo(loginAccountInfo);
        rt.setRights(rights);
        rt.setUserInfo(userInfo);
        return rt;
    }

    public static LoginResponse fail(String message){
        LoginResponse rt = new LoginResponse();
        rt.setSuccess(false);
        rt.setMessage(message);
        return rt;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public LoginInfoDTO getLoginAccountInfo() {
        return loginAccountInfo;
    }

    public void setLoginAccountInfo(LoginInfoDTO loginAccountInfo) {
        this.loginAccountInfo = loginAccountInfo;
    }

    public UserInfoDO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoDO userInfo) {
        this.userInfo = userInfo;
    }
}
