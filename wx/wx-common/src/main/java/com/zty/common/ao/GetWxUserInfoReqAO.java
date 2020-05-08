package com.zty.common.ao;

/**
 * @author tianyi
 * @date 2020-05-02 19:20
 */
public class GetWxUserInfoReqAO {

    private String access_token;

    private String openid;

    private String lang = "zh_CN";

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
