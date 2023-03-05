package com.zty.msg.dto;

import java.io.Serializable;

import com.zty.msg.enums.SmsStatusEnum;

/**
 * 短信手机号通道请求结果
 * @author devwang
 * @date 2019/12/31 10:45
 */
public class MobileRespInfoDTO implements Serializable {

    public MobileRespInfoDTO(String smsRecordId, String mobile, SmsStatusEnum status, String failMsg) {
        this.smsRecordId = smsRecordId;
        this.mobile = mobile;
        this.status = status;
        this.failMsg = failMsg;
    }

    public MobileRespInfoDTO(String smsRecordId, String mobile) {
        this.smsRecordId = smsRecordId;
        this.mobile = mobile;
    }

    public MobileRespInfoDTO() {
    }

    /**
     * 消息id
     */
    private String smsRecordId;

    /**
     * 电话号码
     */
    private String mobile;

    /**
     * 消息状态 状态 1-已发送 2-发送失败
     */
    private SmsStatusEnum status;

    /**
     * 入库用的状态
     * 0：成功 1：待发送 2：发送中 3：失败
     */
    private Integer msgStatus;

    /**
     * 错误消息
     */
    private String failMsg;

    /**
     * 返回的短信id
     */
    private String respSmsId;

    public String getSmsRecordId() {
        return smsRecordId;
    }

    public void setSmsRecordId(String smsRecordId) {
        this.smsRecordId = smsRecordId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public SmsStatusEnum getStatus() {
        return status;
    }

    public void setStatus(SmsStatusEnum status) {
        this.status = status;
    }

    public String getFailMsg() {
        return failMsg;
    }

    public void setFailMsg(String failMsg) {
        this.failMsg = failMsg;
    }

    public String getRespSmsId() {
        return respSmsId;
    }

    public void setRespSmsId(String respSmsId) {
        this.respSmsId = respSmsId;
    }

    public Integer getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(Integer msgStatus) {
        this.msgStatus = msgStatus;
    }
}
