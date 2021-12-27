package com.zty.pay.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author: tianyi.zeng
 * @create: 4/12/2021 - 下午 9:39
 */
public class OrderUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyMMdd");

    public static Long getOrderId() {
        StringBuilder sb = new StringBuilder();
        sb.append(DATE_FORMAT.format(LocalDateTime.now()));  //固定日期位
        sb.append("000000"); //预留标识位
        sb.append(RandomStringUtils.random(6, false, true)); //随机位
        return Long.valueOf(sb.toString());
    }
}
