package com.zty.bo.api;

import com.zty.bo.service.KVService;
import com.zty.common.constant.PrefixConstant;
import com.zty.common.constant.SpliterConstant;

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

    private static final Logger log = LoggerFactory.getLogger(LoginCacheApi.class);

    @Autowired
    private KVService kvService;

    public void setLoginAccountToken(String token,
                                     int userId,
                                     Integer roleId){
        kvService.set(PrefixConstant.LOGIN_TOKEN + token,
                userId+ SpliterConstant.ACCOUNT_AND_ROLE +roleId,
                21600,
                TimeUnit.SECONDS);
    }

    public String getCacheLoginInfoByToken(String token){
        String value = kvService.get(PrefixConstant.LOGIN_TOKEN + token);
        return value == null ? "" : value;
    }
}
