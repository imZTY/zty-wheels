package com.zty.pay.service;

import com.github.pagehelper.Page;
import com.zty.pay.DO.OrderInfoDO;

/**
 * 支付中心，专属订单服务
 * @author tianyi
 * @date 2021-04-18 12:08
 */
public interface PayOrderService {

    /**
     * 检查并返回已存在的订单
     * @param orderInfoDO
     * @return 如果不存在，返回null
     */
    public OrderInfoDO checkAndGet(OrderInfoDO orderInfoDO);

    /**
     * 分页查询充值订单
     * @param pageDo
     * @return
     */
    public Page<OrderInfoDO> pageOrderListByPage(OrderInfoDO pageDo);

    /**
     * 创建新订单(初始 未支付)
     * @param orderInfoDO 订单信息
     * @return 系统订单号(主键)
     */
    public long createNewOrder(OrderInfoDO orderInfoDO);

    /**
     * 更新订单信息
     * @param orderInfoDO 订单信息
     * @return 影响条数
     */
    public int updateOrder(OrderInfoDO orderInfoDO);
}
