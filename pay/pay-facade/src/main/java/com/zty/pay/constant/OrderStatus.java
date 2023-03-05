package com.zty.pay.constant;

import com.zty.framework.exception.ParamCheckException;

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
     * 已终止
     */
    public static final byte FINISHED = 8;

    /**
     * 检查状态是否是终态
     * @param status
     * @return
     */
    public static boolean isFinalStatus(byte status) {
        return status == CLOSED || status == REFUNDED || status == REVERSED || status == FINISHED;
    }

    /**
     * 检查状态是否可支付
     * @param status
     * @return
     */
    public static boolean canPay(byte status) {
        return status == INIT;
    }

    public static byte fromTradeStatus(String tradeStatus) {
        switch (tradeStatus) {
            // 交易创建，等待买家付款
            case "WAIT_BUYER_PAY": return INIT;
            // 未付款交易超时关闭，或支付完成后全额退款
            case "TRADE_CLOSED": return CLOSED;
            // 交易支付成功
            case "TRADE_SUCCESS": return SUCCESS;
            // 交易结束，不可退款
            case "TRADE_FINISHED": return FINISHED;
            default: throw new ParamCheckException("订单状态"+tradeStatus+"解析异常");
        }
    }
}
