package com.zty.framework.exception;

import java.io.Serializable;

/**
 * @author tianyi
 * @date 2020-05-05 17:28
 */
public class CommonException extends RuntimeException implements Serializable {

    private String errorCode;

    private String errorMsg;

    public CommonException(String errorCode, String errorMsg) {
        super(errorCode+":"+errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CommonException(String errorCode, String errorMsg, Throwable cause) {
        super(errorCode+":"+errorMsg, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
