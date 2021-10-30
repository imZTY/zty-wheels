package com.zty.msg.dto;

import java.io.Serializable;

/**
 * @author: tianyi.zeng
 * @create: 20/10/2021 - 上午 9:36
 */
public class MobileReqInfoDTO implements Serializable {

    /**
     * 消息id
     */
    private String smsRecordId;

    /**
     * 电话号码
     */
    private String mobile;

    public MobileReqInfoDTO() {
    }

    public MobileReqInfoDTO(String smsRecordId, String mobile) {
        this.smsRecordId = smsRecordId;
        this.mobile = mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public String getSmsRecordId() {
        return smsRecordId;
    }

    public void setSmsRecordId(String smsRecordId) {
        this.smsRecordId = smsRecordId;
    }
}

