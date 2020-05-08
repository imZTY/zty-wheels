package com.zty.wheel.aop;

import com.zty.bo.api.LoginCacheApi;
import com.zty.framework.dto.DataDTO;
import com.zty.framework.dto.ResultDTO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tianyi
 * @date 2020-05-03 15:49
 */
@Aspect
@Component
public class LoginCheck {

    Logger log = LoggerFactory.getLogger(LoginCheck.class);

    @Autowired
    private LoginCacheApi loginCacheApi;

    @Pointcut("@annotation(com.zty.framework.annotation.CheckToken)")
    public void check() {

    }

    @Around("check()")
    public Object preHandle(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        String token = null;

        //原参数
        Object[] originArgs = joinPoint.getArgs();

        if (cookies != null) {
            for (Cookie cookie : cookies) {  // 从cookie中取出 token
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if (!StringUtils.isBlank(token)){
            int userId = loginCacheApi.getCacheUserIdByToken(token);
            if (userId == 0){
                // log.error("USERID is 0");
                return ResultDTO.error(501,"token无效");
            }
            ((DataDTO)originArgs[0]).setCurrentUID(userId);
        }else {
            return ResultDTO.error(501,"无token，请登录");
        }
        try {
            return joinPoint.proceed(originArgs);
        } catch (Throwable throwable) {  // 若原方法执行出错，这里也可以截取到异常
            // log.error("系统出错：", throwable);
            throwable.printStackTrace();
            log.error("RuntimeException ", throwable);
            return ResultDTO.error(500,"系统异常");
        }
    }
}
