package com.zty.framework.exception;

/**
 * 业务处理异常
 * @author: tianyi.zeng
 * @create: 18/8/2021 - 下午 11:20
 */
public class BusinessException extends CommonException{

    public BusinessException(String errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }

    public BusinessException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode, errorMsg, cause);
    }
}
