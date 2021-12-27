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
    public static final byte REFUNDING = 4;

    /**
     * 已退款
     */
    public static final byte REFUNDED = 4;

    /**
     * 已冲正
     */
    public static final byte REVERSED = 4;
}
