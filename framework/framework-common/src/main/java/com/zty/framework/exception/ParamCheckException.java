package com.zty.framework.exception;

/**
 * 参数检查异常
 * @author: tianyi.zeng
 * @create: 18/8/2021 - 下午 11:19
 */
public class ParamCheckException extends CommonException {

    private String keyName;

    private String value;


    public ParamCheckException(String errorMsg) {
        super("ParamCheckException", errorMsg);
    }

    public ParamCheckException(String errorMsg, String keyName, String value) {
        super("ParamCheckException", errorMsg);
        this.keyName = keyName;
        this.value = value;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
