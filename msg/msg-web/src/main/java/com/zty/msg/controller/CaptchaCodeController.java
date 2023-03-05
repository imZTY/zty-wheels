package com.zty.msg.controller;

import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码相关接口
 */
@RestController
@RequestMapping("/msg")
public class CaptchaCodeController {

    private static final Logger log = LoggerFactory.getLogger(CaptchaCodeController.class);

    @Autowired
    private ConfigurableCaptchaService captchaService;

    @GetMapping({"/getCaptchaCode"})
    public void getCaptchaCode(@RequestParam("randomToken") String randomToken, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0L);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        String capText = EncoderHelper.getChallangeAndWriteImage(this.captchaService, "png", out);
//        this.loginSecurityService.setLoginCaptchaCode(randomToken, capText);
        log.info("capText={}", capText);
        try {
            out.flush();
        } finally {
            out.close();
        }

    }
}
