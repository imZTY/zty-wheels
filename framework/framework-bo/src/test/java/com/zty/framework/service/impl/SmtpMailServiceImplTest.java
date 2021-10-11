package com.zty.framework.service.impl;

import java.util.Arrays;
import java.util.Collections;

import com.zty.framework.request.SmtpMailRequest;
import com.zty.framework.response.SmtpMailResponse;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmtpMailServiceImplTest {

    @Test
    public void sendEMailTest() {
        SmtpMailServiceImpl mailService = new SmtpMailServiceImpl();
        SmtpMailRequest request = new SmtpMailRequest()
                .fromMail("tianyi.zeng@swiftpass.cn")
                .toMails(Arrays.asList("tianyi.zeng@swiftpass.cn"))
                .ccMails(Collections.singletonList("tianyi.zeng@swiftpass.cn"))
                .subject("单元测试 - sendEMail")
                .content("邮件正文");
        SmtpMailResponse response = mailService.sendEMail(request);
        assertEquals(true, response.isSuccess());
    }
}