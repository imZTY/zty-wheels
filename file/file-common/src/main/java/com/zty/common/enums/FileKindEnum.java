package com.zty.common.enums;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tianyi
 * @date 2021-04-08 00:53
 */
public enum FileKindEnum {

    UNKNOW((byte)0, "未知"),
    ID_CARD_FRONT((byte)1, "身份证正面"),
    ID_CARD_BACK((byte)2, "身份证反面"),
    BUSINESS_LICENSE((byte)3, "营业执照"),
    MAN_WITH_LICENSE((byte)4, "人与证件合影")
    ;

    private static final Logger log = LoggerFactory.getLogger(FileKindEnum.class);

    FileKindEnum(byte value, String message) {
        this.value = value;
        this.message = message;
    }


    /**
     * 获取枚举
     * @param value 文件类型
     * @param throwIfAbsent 若无法匹配，是否抛出异常
     * @date 2020/11/02 18:20:17
     * @return
     */
    public static FileKindEnum getByVelue(byte value, boolean throwIfAbsent) {
        for (FileKindEnum type : FileKindEnum.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        if (throwIfAbsent) {
            log.error("无法识别传入的文件类型，{}", value);
            throw new IllegalArgumentException("无法识别文件类型"+value);
        }
        return null;
    }

    private byte value;

    private String message;

    /**
     * 检查文件种类是否合法
     * @param fileKind
     * @return
     */
    static public boolean checkExist(byte fileKind) {
        return Arrays.stream(FileKindEnum.values())
                .anyMatch(e -> fileKind == e.getValue());
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
