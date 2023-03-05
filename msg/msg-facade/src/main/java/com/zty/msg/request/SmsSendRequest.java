package com.zty.msg.request;


import java.util.List;

import com.zty.msg.dto.MobileReqInfoDTO;

/**
 * 短信发送请求参数AO
 * @author devwang
 * @date 2019/12/30 20:39
 */
public class SmsSendRequest {
    private static final long SERIAL_VERSION_UID = 1L;

    /**
     * 手机号和短信ID的对应关系
     */
    private List<MobileReqInfoDTO> mobileReqInfoDTOList;
    /**
     * 参数列表
     */
    private List<String> paramList;
    /**
     * 短信实际发送内容
     */
    private String content;
    /**
     * 冠码
     */
    private String nationCode;
    /**
     * 第三方短信模板ID
     */
    private Long templateId;
    /**
     * 第三方短信模板ID
     */
    private String sign;
    /**
     * 通道配置项
     */
    private String configJson;
    /**
     * 第三方通道接口地址
     */
    private String url;
    /**
     * 请求超时时间（毫秒）
     */
    private Integer timeout;
    /**
     * 代理IP
     */
    private String proxyHost;
    /**
     * 代理端口
     */
    private Integer proxyPort;
    /**
     * 短信订单ID
     */
    private String batchId;

    /**
     * 连接超时
     */
    private  Integer connectTimeout;

    /**
     * 连接池连接时间
     */
    private Integer conReqTimeout;

    public static long getSerialVersionUid() {
        return SERIAL_VERSION_UID;
    }

    public List<MobileReqInfoDTO> getMobileReqInfoDTOList() {
        return mobileReqInfoDTOList;
    }

    public void setMobileReqInfoDTOList(List<MobileReqInfoDTO> mobileReqInfoDTOList) {
        this.mobileReqInfoDTOList = mobileReqInfoDTOList;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getConfigJson() {
        return configJson;
    }

    public void setConfigJson(String configJson) {
        this.configJson = configJson;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getConReqTimeout() {
        return conReqTimeout;
    }

    public void setConReqTimeout(Integer conReqTimeout) {
        this.conReqTimeout = conReqTimeout;
    }
}
