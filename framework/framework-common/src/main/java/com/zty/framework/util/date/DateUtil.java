package com.zty.framework.util.date;

import java.time.LocalDateTime;

/**
 * @author tianyi
 * @date 2020-05-23 15:34
 */
public class DateUtil {

    public static int getYear(){
        return LocalDateTime.now().getYear();
    }

    public static int getMonth(){
        return LocalDateTime.now().getMonthValue();
    }

    public static int getWeekOfYear(){
        LocalDateTime time = LocalDateTime.now();
        int dayOfWeek = time.getDayOfWeek().getValue();
        int dayOfYear = time.getDayOfYear();
//        if (dayOfWeek > dayOfYear){
//            return 0;
//        }
//        int weekOfYear = (dayOfYear - dayOfWeek) / 7 + 1;
        return dayOfWeek > dayOfYear ? 0 :(dayOfYear - dayOfWeek) / 7 + 1;
    }
}
