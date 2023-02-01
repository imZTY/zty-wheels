package com.zty.framework.util;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

import static java.util.Objects.requireNonNull;

/**
 * java.time.LocalDateTime工具类，包含一些常用的/原生方法里没有的方法 LocalDateTime里面提供了大大量方便使用的方法
 *
 * <pre>
 *     # 取当前时间
 *     LocalDateTime now = LocalDateTime.now();
 *     加减天数， 参数都可以是负数
 *     LocalDateTime relativeDay1 = now.plusDays(-2);
 *     LocalDateTime relativeDay2 = now.minusDays(2);
 *     # 过去最近的一个周一（包括今天）， 也就是本周开始。
 *     LocalDateTime startOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
 *     # 周日， 或者本周最后一天
 *     LocalDateTime endOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
 *     # {@link TemporalAdjusters} 还有很多如本月第一天之类的
 *
 *     # 计算差值
 *     #LocalDateTime otherTime;
 *     #使用util
 *     LocalDateTime.now().until(otherTime, ChronoUnit.DAYS);
 *     # 使用 between
 *     Duration.between(now, otherTime)
 *
 *     对于有时区的可以使用 {@link java.time.ZonedDateTime} {@link java.time.OffsetDateTime}
 *
 *     对对字符串转换常用的Pattern:
 *     rfc3339(iso date time)  "2021-03-01T10:00:20.021+08:00" 使用 {@link DateTimeFormatter#ISO_DATE_TIME}
 *
 *    不带时区的 如 2011-12-03T10:15:30 使用{@link java.time.format.DateTimeFormatter#ISO_LOCAL_DATE_TIME}
 *
 *  </pre>
 *
 * @author Tianyi.Zeng
 * @date 8/2/2021 - 下午 5:41
 */
public final class LocalDateTimeUtils {

    /**
     * 隐藏无参构造器.
     */
    private LocalDateTimeUtils() {}

    /**
     * 将毫秒时间戳转成 LocalDateTime.
     * @param epochMilli the number of milliseconds from 1970-01-01T00:00:00Z
     */
    public static LocalDateTime ofEpochMilli(long epochMilli) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
    }

    /**
     * 获取当天开始时间.
     * @param localDateTime 一个时间
     */
    public static LocalDateTime startOfDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atStartOfDay();
    }

    /**
     * 获取次日开始时间.
     * @param localDateTime 一个时间
     */
    public static LocalDateTime startOfNextDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().plusDays(1).atStartOfDay();
    }

    /**
     * 获取下一个工作日.
     */
    public static LocalDateTime getNextWorkDay() {
        return getNextWorkDay(LocalDateTime.now());
    }

    /**
     * 获取某一天的下一个工作日.
     * @param localDateTime 一个时间
     */
    public static LocalDateTime getNextWorkDay(LocalDateTime localDateTime) {
        // 默认返回第二天
        LocalDateTime nextWorkDay = startOfDay(localDateTime.plusDays(1));
        // 若第二天是周末，则返回下一个周一
        if (DayOfWeek.SATURDAY == nextWorkDay.getDayOfWeek() || DayOfWeek.SUNDAY == nextWorkDay.getDayOfWeek()) {
            nextWorkDay = startOfDay(nextWorkDay.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        }
        return nextWorkDay;
    }

    /**
     * rfc3339的 时间格式。转换成当前时区. 格式如 "2021-03-01T10:00:20.021+08:00" 实际是先解成 ZonedDateTime 再转成
     * localDatetime.
     */
    public static LocalDateTime fromRfc3339(String timeStr) {
        requireNonNull(timeStr, "timeStr");
        return ZonedDateTime.parse(timeStr)
                .withZoneSameInstant(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    /**
     * rfc3339的 时间格式。使用了当前系统的时区 格式如 "2021-03-01T10:00:20.021+08:00"
     */
    public static String toRfc3339(LocalDateTime localDateTime) {
        requireNonNull(localDateTime, "localDateTime");
        return localDateTime
                .atOffset(ZoneId.systemDefault().getRules().getOffset(localDateTime))
                .format(DateTimeFormatter.ISO_DATE_TIME);
    }
}

