package com.zty.framework.exception;

/**
 * @author: tianyi.zeng
 * @create: 18/8/2021 - 下午 11:21
 */
public class NetworkException extends CommonException {

    public NetworkException(String errorMsg) {
        super("NetWorldException", errorMsg);
    }

    public NetworkException(String errorMsg, Throwable cause) {
        super("NetWorldException", errorMsg, cause);
    }

}
