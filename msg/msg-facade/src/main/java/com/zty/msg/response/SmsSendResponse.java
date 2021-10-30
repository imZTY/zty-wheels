package com.zty.msg.response;

import java.util.List;

import com.zty.msg.dto.MobileRespInfoDTO;

/**
 * 短信发送返回参数VO
 */
public class SmsSendResponse {
    private static final long SERIAL_VERSION_UID = 1L;

    /**
     * 短信批次ID
     */
    private String batchId;

    /**
     * 状态：00000000：请求成功  10000001:部分成功 其他：请求失败
     */
    private String status;

    /**
     * 部分成功标识
     */
    private Boolean portionSuccessStatus = false;

    /**
     * 状态描述
     */
    private String description;

    /**
     * 第三方返回状态码
     */
    private String outStatus;

    /**
     * 第三方返回状态描述
     */
    private String outDescription;

    private List<MobileRespInfoDTO> mobileRespInfoDTOList;

    public static long getSerialVersionUid() {
        return SERIAL_VERSION_UID;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MobileRespInfoDTO> getMobileRespInfoDTOList() {
        return mobileRespInfoDTOList;
    }

    public void setMobileRespInfoDTOList(List<MobileRespInfoDTO> mobileRespInfoDTOList) {
        this.mobileRespInfoDTOList = mobileRespInfoDTOList;
    }

    public String getOutStatus() {
        return outStatus;
    }

    public void setOutStatus(String outStatus) {
        this.outStatus = outStatus;
    }

    public String getOutDescription() {
        return outDescription;
    }

    public void setOutDescription(String outDescription) {
        this.outDescription = outDescription;
    }

    public Boolean getPortionSuccessStatus() {
        return portionSuccessStatus;
    }

    public void setPortionSuccessStatus(Boolean portionSuccessStatus) {
        this.portionSuccessStatus = portionSuccessStatus;
    }
}