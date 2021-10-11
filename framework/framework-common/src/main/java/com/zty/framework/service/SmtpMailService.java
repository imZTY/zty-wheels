package com.zty.framework.service;

import com.zty.framework.request.SmtpMailRequest;
import com.zty.framework.response.SmtpMailResponse;

/**
 * 基于smtp协议发送邮件的接口定义.
 */
public interface SmtpMailService {

    /**
     * 发送电子邮件.
     */
    public SmtpMailResponse sendEMail(SmtpMailRequest request);

}
