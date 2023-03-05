package com.zty.framework.response;

/**
 * 基于smtp协议发送邮件的响应类.
 */
public class SmtpMailResponse {
    private boolean success;
    private String errorMsg;

    public SmtpMailResponse() {
    }

    public SmtpMailResponse(boolean success) {
        this.success = success;
    }

    public SmtpMailResponse(boolean success, String errorMsg) {
        this.success = success;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
