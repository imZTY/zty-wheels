package com.zty.pay.factory;

import com.zty.pay.config.AlipayConfig;
import com.zty.pay.config.BestpayConfig;
import com.zty.pay.config.WechatConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: tianyi.zeng
 * @create: 25/11/2021 - 下午 10:30
 */
@Configuration
@PropertySource(value = "payment.properties")
public class PayConfigFactory {

    // 支付宝配置
    @Value("${payment.alipay.appid:}")
    private String alipayAppid;
    @Value("${payment.alipay.private_key:}")
    private String alipayPrivateKey;
    @Value("${payment.alipay.alipay_public_key:}")
    private String alipayPublicKey;

    @Value("${payment.alipay.notifyUrl:http://你的异步处理地址/alipay/notify_async}")
    private String notifyUrl;

    @Value("${payment.alipay.returnUrl:http://你的同步处理地址/alipay/notify_sync}")
    private String returnUrl;

    @Bean
    public AlipayConfig alipayConfig() {
        AlipayConfig alipayConfig = new AlipayConfig();
        alipayConfig.setAppid(alipayAppid);
        alipayConfig.setPrivate_key(alipayPrivateKey);
        alipayConfig.setAlipay_public_key(alipayPublicKey);
        alipayConfig.setNotifyUrl(this.notifyUrl);
        alipayConfig.setRetrunUrl(this.returnUrl);
        return alipayConfig;
    }

    // 微信支付配置
    private String wxAppid;
    private String wxAppSecret;
    private String wxApiKey;
    private String wxMchId;
    private String wxSubMchId;

    @Bean
    public WechatConfig wechatConfig() {
        WechatConfig wechatConfig = new WechatConfig();
        return wechatConfig;
    }

    // 翼支付配置
    private String bestpayKey;
    private String bestpayMerchantPwd;
    private String bestpayMerchantId;

    @Bean
    public BestpayConfig bestpayConfig() {
        BestpayConfig bestpayConfig = new BestpayConfig();
        return bestpayConfig;
    }

    public String getAlipayAppid() {
        return alipayAppid;
    }

    public void setAlipayAppid(String alipayAppid) {
        this.alipayAppid = alipayAppid;
    }

    public String getAlipayPrivateKey() {
        return alipayPrivateKey;
    }

    public void setAlipayPrivateKey(String alipayPrivateKey) {
        this.alipayPrivateKey = alipayPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        this.alipayPublicKey = alipayPublicKey;
    }

    public String getWxAppid() {
        return wxAppid;
    }

    public void setWxAppid(String wxAppid) {
        this.wxAppid = wxAppid;
    }

    public String getWxAppSecret() {
        return wxAppSecret;
    }

    public void setWxAppSecret(String wxAppSecret) {
        this.wxAppSecret = wxAppSecret;
    }

    public String getWxApiKey() {
        return wxApiKey;
    }

    public void setWxApiKey(String wxApiKey) {
        this.wxApiKey = wxApiKey;
    }

    public String getWxMchId() {
        return wxMchId;
    }

    public void setWxMchId(String wxMchId) {
        this.wxMchId = wxMchId;
    }

    public String getWxSubMchId() {
        return wxSubMchId;
    }

    public void setWxSubMchId(String wxSubMchId) {
        this.wxSubMchId = wxSubMchId;
    }

    public String getBestpayKey() {
        return bestpayKey;
    }

    public void setBestpayKey(String bestpayKey) {
        this.bestpayKey = bestpayKey;
    }

    public String getBestpayMerchantPwd() {
        return bestpayMerchantPwd;
    }

    public void setBestpayMerchantPwd(String bestpayMerchantPwd) {
        this.bestpayMerchantPwd = bestpayMerchantPwd;
    }

    public String getBestpayMerchantId() {
        return bestpayMerchantId;
    }

    public void setBestpayMerchantId(String bestpayMerchantId) {
        this.bestpayMerchantId = bestpayMerchantId;
    }
}
