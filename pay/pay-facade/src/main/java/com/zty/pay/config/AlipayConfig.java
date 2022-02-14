package com.zty.pay.config;

/**
 * 支付宝配置
 * @author HanleyTang
 * @date 2018-12-03
 */
public class AlipayConfig {

    private String appid;
    private String private_key;
    private String alipay_public_key;

    private String notifyUrl;

    private String retrunUrl;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    public String getAlipay_public_key() {
        return alipay_public_key;
    }

    public void setAlipay_public_key(String alipay_public_key) {
        this.alipay_public_key = alipay_public_key;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AlipayConfig{");
        sb.append("appid='").append(appid).append('\'');
        sb.append(", private_key='").append(private_key).append('\'');
        sb.append(", alipay_public_key='").append(alipay_public_key).append('\'');
        sb.append(", notifyUrl='").append(notifyUrl).append('\'');
        sb.append(", retrunUrl='").append(retrunUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getRetrunUrl() {
        return retrunUrl;
    }

    public void setRetrunUrl(String retrunUrl) {
        this.retrunUrl = retrunUrl;
    }
}
