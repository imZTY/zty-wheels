package com.zty.common.dto;

/**
 * 与微信系统交互结果类
 * @author tianyi
 * @date 2020-05-02 19:33
 */
public class WxResultDTO {

    private boolean success = false;

    private Object data;

    public static WxResultDTO success(WxDataDTO data){
        WxResultDTO rt = new WxResultDTO();
        rt.setSuccess(true);
        rt.setData(data);
        return rt;
    };

    public static WxResultDTO fail(WxErrorDTO data){
        WxResultDTO rt = new WxResultDTO();
        rt.setSuccess(false);
        rt.setData(data);
        return rt;
    };

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
