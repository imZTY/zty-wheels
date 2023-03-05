package com.zty.pay.service;

import com.zty.common.dto.MessageDTO;
import com.zty.pay.DO.OrderInfoDO;

/**
 * @author: tianyi.zeng
 * @create: 2/4/2022 - 下午 9:09
 */
public interface OrderMqService {

    /**
     * 从支付回调mq里取出消息
     * @param business 消息对象
     * @return
     */
    public OrderInfoDO popFromPayNotify(String business);

    /**
     * 将订单消息放入支付回调mq
     * @param business 业务代号
     * @param messageDTO 消息对象
     * @return
     */
    public boolean pushToPayNotify(String business, MessageDTO messageDTO);
}
