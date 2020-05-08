package com.zty.common.dto;

/**
 * @author tianyi
 * @date 2020-05-02 19:06
 */
public class WxErrorDTO extends WxDataDTO {

    private Integer errcode;

    private String errmsg;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
