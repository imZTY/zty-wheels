package com.zty.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tianyi
 * @date 2020-05-22 20:36
 */
@Component
public class TokenConfig {

    @Value("${zty-framework.token.path}")
    private String path;

    @Value("${zty-framework.token.expires}")
    private Integer expires;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getExpires() {
        return expires;
    }

    public void setExpires(Integer expires) {
        this.expires = expires;
    }
}
