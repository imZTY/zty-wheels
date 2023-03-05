package com.zty.pay.controller;

import java.util.Date;

import com.alipay.api.AlipayConstants;
import com.zty.framework.dto.ResultDTO;
import com.zty.framework.exception.ParamCheckException;
import com.zty.pay.DO.OrderInfoDO;
import com.zty.pay.config.AlipayConfig;
import com.zty.pay.constant.OrderStatus;
import com.zty.pay.service.PayOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thlws.payment.AlipayClient;
import org.thlws.payment.alipay.core.AlipayCore;
import org.thlws.payment.alipay.entity.response.AlipayQueryResponse;

/**
 * 订单聚合接口
 * @author: tianyi.zeng
 * @create: 13/1/2022 - 下午 9:39
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private PayOrderService payOrderService;

    @PostMapping("/query")
    public ResultDTO queryOrder(@RequestParam(name = "orderId",defaultValue = "0")String orderId) {
        OrderInfoDO localOrder = null;
        try {
            // 检查本地订单状态
            OrderInfoDO queryOrder = new OrderInfoDO();
            queryOrder.setId(Long.valueOf(orderId));
            localOrder = payOrderService.checkAndGet(queryOrder);
            if (localOrder == null) {
                throw new ParamCheckException("订单不存在", "orderId", orderId);
            }
            if (OrderStatus.SUCCESS == localOrder.getStatus() || OrderStatus.isFinalStatus(localOrder.getStatus())) {
                log.info("订单是终态:{}, 直接返回", localOrder.getStatus());
                return ResultDTO.success(localOrder);
            }
        } catch (Exception e) {
            log.error("本地订单处理异常, ", e);
            return ResultDTO.error(500, "本地订单处理异常");
        }
        try {
            AlipayCore.ClientBuilder clientBuilder = new AlipayCore.ClientBuilder();
            AlipayCore alipayCore = clientBuilder.setAlipayPublicKey(alipayConfig.getAlipay_public_key())
                    .setAppId(alipayConfig.getAppid())
                    .setPrivateKey(alipayConfig.getPrivate_key())
                    .setSignType(AlipayConstants.SIGN_TYPE_RSA2).build();

            AlipayQueryResponse response = AlipayClient.query(String.valueOf(localOrder.getIdValue()), alipayCore);
            if (response.isSuccess()) {
                updateOrderData(localOrder, response);
            } else {
                log.warn("订单查询不成功, orderId:{}, response:{}", localOrder.getIdValue(), response);
                return ResultDTO.error(500, "第三方响应失败," + response.getCode() + ":" + response.getDesc() + ":" + response.getSubCode());
            }
            localOrder.setUpdateTime(new Date());
            payOrderService.updateOrder(localOrder);
        } catch (Exception e) {
            log.error("跟进订单状态异常, ", e);
            return ResultDTO.error(500, "跟进订单状态异常");
        }
        return ResultDTO.success(localOrder);
    }

    /**
     * 更新本地订单字段
     * @param localOrder 本地订单数据
     * @param response 第三方响应数据
     */
    private void updateOrderData(OrderInfoDO localOrder, AlipayQueryResponse response) {
        localOrder.setStatus(OrderStatus.fromTradeStatus(response.getTradeStatus()));
        localOrder.setThirdOrderNo(response.getTradeNo());
        localOrder.setRemarks(response.getBuyerLogonId());
    }
}
