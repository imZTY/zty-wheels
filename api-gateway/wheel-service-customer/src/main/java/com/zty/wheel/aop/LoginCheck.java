package com.zty.wheel.aop;

import java.lang.reflect.Method;

import com.zty.bo.api.LoginCacheApi;
import com.zty.common.constant.SpliterConstant;
import com.zty.common.service.RoleService;
import com.zty.framework.annotation.HasRights;
import com.zty.framework.dto.DataDTO;
import com.zty.framework.dto.ResultDTO;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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

    private static final Logger log = LoggerFactory.getLogger(LoginCheck.class);

    @Autowired
    private LoginCacheApi loginCacheApi;

    @Autowired
    private RoleService roleService;

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
            String[] value = loginCacheApi.getCacheLoginInfoByToken(token).split("["+SpliterConstant.ACCOUNT_AND_ROLE+"]");
            if (value.length < 2) {
                return ResultDTO.error(501,"token无效");
            }
            log.debug("缓存token的值：{}", value);
            int userId = Integer.parseInt(value[0]);
            Integer roleId = Integer.parseInt(value[1]);
            if (userId == 0){
                // log.error("USERID is 0");
                return ResultDTO.error(501,"token无效");
            }
            // 设置当前账号的id到currentUID
            ((DataDTO)originArgs[0]).setCurrentUID(userId);

            // 权限检验
            Signature signature = joinPoint.getSignature();
            MethodSignature methodSignature = (MethodSignature) signature;
            Method method = methodSignature.getMethod();
            HasRights annotation = null;
            try {
                annotation = (HasRights)method.getAnnotation(HasRights.class);
            }catch (NullPointerException npe){
                // 无HasRights注解
            }
            if (annotation != null) {
                String[] rights = annotation.rights().split(SpliterConstant.HASRIGHTS_SPLIT);
                //权限判断
                if (!roleService.checkRight(roleId, rights)) {
                    return ResultDTO.error(500, "你没有这个权限，请联系管理员");
                }
            }
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
