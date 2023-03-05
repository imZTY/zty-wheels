package com.zty.pay.service.impl;

import com.zty.bo.service.ListService;
import com.zty.common.dto.MessageDTO;
import com.zty.pay.DO.OrderInfoDO;
import com.zty.pay.constant.MqConstant;
import com.zty.pay.service.OrderMqService;
import com.zty.pay.service.PayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: tianyi.zeng
 * @create: 2/4/2022 - 下午 9:19
 */
@Service
public class OrderMqServiceImpl implements OrderMqService {

    private static final Logger log = LoggerFactory.getLogger(OrderMqServiceImpl.class);

    @Autowired
    private ListService redisListService;

    @Autowired
    private PayOrderService payOrderService;

    /**
     * 从业务mq里取出消息
     *
     * @param business 消息对象
     * @return
     */
    @Override
    public OrderInfoDO popFromPayNotify(String business) {
        MessageDTO messageDTO = redisListService.pop(MqConstant.PAY_NOTIFY_QUEUE_PREFIX + business);
        log.info("从【{}】取出消息:{}", business, messageDTO);
        OrderInfoDO query = new OrderInfoDO();
        query.setId(Long.valueOf(messageDTO.getData()));
        OrderInfoDO order = payOrderService.checkAndGet(query);
        log.info("获取本地订单,结果:{}", order);
        return order;
    }

    /**
     * 将订单消息放入业务mq
     *
     * @param business   业务代号
     * @param messageDTO 消息对象
     * @return
     */
    @Override
    public boolean pushToPayNotify(String business, MessageDTO messageDTO) {
        log.info("向【{}】放入消息:{}", business, messageDTO);
        int num = redisListService.push(MqConstant.PAY_NOTIFY_QUEUE_PREFIX + business, messageDTO);
        return num == 1;
    }
}
