package com.zty.pay.constant;

/**
 * 订单状态
 * @author tianyi
 * @date 2021-04-21 02:03
 */
public class OrderStatus {

    /**
     * 初始
     */
    public static final byte INIT = 0;

    /**
     * 处理中
     */
    public static final byte DEALING = 1;

    /**
     * 支付完成
     */
    public static final byte SUCCESS = 2;

    /**
     * 支付关闭
     */
    public static final byte CLOSED = 3;

    /**
     * 失败
     */
    public static final byte FAIL = 4;

    /**
     * 退款中
     */
    public static final byte REFUNDING = 5;

    /**
     * 已退款
     */
    public static final byte REFUNDED = 6;

    /**
     * 已冲正
     */
    public static final byte REVERSED = 7;

    /**
     * 检查状态是否是终态
     * @param status
     * @return
     */
    public static boolean isFinalStatus(byte status) {
        return status == CLOSED || status == REFUNDED || status == REVERSED;
    }

    /**
     * 检查状态是否可支付
     * @param status
     * @return
     */
    public static boolean canPay(byte status) {
        return status == INIT;
    }
}
