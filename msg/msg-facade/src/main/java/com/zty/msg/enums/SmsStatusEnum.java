package com.zty.msg.enums;

/**
 * 短信第三方通道返回状态r
 */
public enum SmsStatusEnum {

    /**
     * 已发送
     */
    SUCCESS(1),
    /**
     * 发送失败
     */
    FAIL(2);


    private Integer value;

    SmsStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}