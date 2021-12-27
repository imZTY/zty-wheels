package com.zty.pay.constant;

/**
 * 订单方式
 * @author tianyi
 * @date 2021-04-21 01:44
 */
public class OrderMethod {

    /**
     * 支付宝
     */
    public static final byte ALI_PAY = 1;

    /**
     * 微信支付
     */
    public static final byte WECHAT_PAY = 2;

    /**
     * 银行卡转账
     */
    public static final byte BANK_TRANSFER = 3;

    /**
     * 超管人工充值
     */
    public static final byte ADMIN = 4;

    public static boolean contains(byte orderMethod) {
        return orderMethod == ALI_PAY
                || orderMethod == WECHAT_PAY
                || orderMethod == BANK_TRANSFER
                || orderMethod == ADMIN;
    }
}
