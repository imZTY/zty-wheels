package com.zty.pay.utils;

import java.math.BigDecimal;

/**
 * @author: tianyi.zeng
 * @create: 22/12/2021 - 下午 11:14
 */
public class MoneyUtil {

    /**
     * fenToYuan:分到元 字符文本
     *
     * @param fen
     * @return
     */
    public static String fenToYuan(Long fen) {
        String value = "0.0";
        if (fen != null) {
            try {
                BigDecimal b1 = new BigDecimal(fen);
                BigDecimal b2 = new BigDecimal(100);
                BigDecimal amount = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
                value = amount.toPlainString();
            } catch (Exception e) {
            }
        }
        return value;
    }

    /**
     * 分转成元，返回BigDecimal
     *
     * @param fen
     * @return
     */
    public static BigDecimal fenToYuanDecimal(Long fen) {
        if (null == fen) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(fen).divide(BigDecimal.valueOf(100L), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 分转成元，返回BigDecimal
     *
     * @param fen
     * @return
     */
    public static BigDecimal fenToYuanDecimal(BigDecimal fen) {
        if (null == fen) {
            return BigDecimal.ZERO;
        }
        return fen.divide(BigDecimal.valueOf(100L), 2, BigDecimal.ROUND_HALF_UP);
    }
}
