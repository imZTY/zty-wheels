package com.zty.common.ao;

/**
 * @author tianyi
 * @date 2020-05-02 19:20
 */
public class CreateTokenReqAO {

    private String code;

    private String appid;

    private String secret;

    private String grant_type = "authorization_code";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
