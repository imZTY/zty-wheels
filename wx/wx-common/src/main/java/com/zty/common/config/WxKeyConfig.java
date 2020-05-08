package com.zty.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tianyi
 * @date 2020-05-02 19:25
 */
@Component
public class WxKeyConfig {

    /**
     * 微信公众号appid
     */
    @Value("${wx.key.appid}")
    private String appid;

    /**
     * 微信公众号secret
     */
    @Value("${wx.key.secret}")
    private String secret;

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }
}
