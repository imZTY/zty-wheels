package com.zty.pay.config;

import java.nio.charset.StandardCharsets;

import cn.hutool.core.net.URLEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 支付中心配置
 * @author: tianyi.zeng
 * @create: 22/12/2021 - 下午 8:59
 */
@Component
public class PayCenterConfig {

    /**
     * 支付宝网站支付，跳转url
     */
    @Value("${paycenter.baseUrl:http://127.0.0.1:8088/paycenter/}")
    private String baseUrl;

    /**
     * 支付宝网站支付，跳转url
     */
    @Value("${paycenter.alipay.webpay:/paycenter/alipay/pay/web}")
    private String alipayWebpay;

    /**
     * 支付宝网站支付，同步回调地址
     */
    @Value("${paycenter.alipay.syncNotofy:alipay/alipay_sync_notify}")
    private String syncNotofy;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getAlipayWebpay() {
        return alipayWebpay;
    }

    public String getSyncNotofy() {
        return syncNotofy;
    }

    public void setSyncNotofy(String syncNotofy) {
        this.syncNotofy = syncNotofy;
    }

    public void setAlipayWebpay(String alipayWebpay) {
        this.alipayWebpay = alipayWebpay;
    }
}
