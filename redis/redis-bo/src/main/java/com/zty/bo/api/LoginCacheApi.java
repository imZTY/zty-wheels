package com.zty.bo.api;

import com.zty.bo.service.KVService;
import com.zty.common.constant.PrefixConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 登录状态缓存操作接口类
 * @author tianyi
 * @date 2020-05-02 21:46
 */
@Service
public class LoginCacheApi {

    Logger log = LoggerFactory.getLogger(LoginCacheApi.class);

    @Autowired
    private KVService kvService;

    public void setTokenAndUserId(String token, int userId){
        kvService.set(PrefixConstant.LOGIN_TOKEN + token, userId+"", 21600, TimeUnit.SECONDS);
    }

    public int getCacheUserIdByToken(String token){
        String value = kvService.get(PrefixConstant.LOGIN_TOKEN + token);
        return value == null ? 0 : Integer.valueOf(value);
    }
}
