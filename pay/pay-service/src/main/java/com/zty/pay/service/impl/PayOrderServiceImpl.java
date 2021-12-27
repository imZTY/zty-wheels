package com.zty.pay.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zty.pay.DO.OrderInfoDO;
import com.zty.pay.DO.example.OrderInfoDOExample;
import com.zty.pay.constant.OrderStatus;
import com.zty.pay.dao.OrderInfoDOMapper;
import com.zty.pay.service.PayOrderService;
import com.zty.pay.util.OrderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tianyi
 * @date 2021-04-18 12:16
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {

    private static final Logger log = LoggerFactory.getLogger(PayOrderServiceImpl.class);

    @Autowired
    private OrderInfoDOMapper orderInfoDOMapper;

    /**
     * 检查并返回已存在的充值订单
     *
     * @param orderInfoDO
     * @return 如果不存在，返回null
     */
    @Override
    public OrderInfoDO checkAndGet(OrderInfoDO orderInfoDO) {
        if (orderInfoDO.getId() != null) {
            return orderInfoDOMapper.selectByPrimaryKey(orderInfoDO.getId());
        }
        OrderInfoDOExample example = new OrderInfoDOExample();
        example.createCriteria()
                .andThirdOrderNoEqualTo(orderInfoDO.getThirdOrderNo())
                .andOrderMethodEqualTo(orderInfoDO.getOrderMethod());
        List<OrderInfoDO> result = orderInfoDOMapper.selectByExample(example);
        return result.size() == 0 ? null : result.get(0);
    }


    /**
     * 分页查询充值订单
     *
     * @param pageDo
     * @return
     */
    @Override
    public Page<OrderInfoDO> pageOrderListByPage(OrderInfoDO pageDo) {
        OrderInfoDOExample example = new OrderInfoDOExample();
        if (pageDo.getId() != null) {
            example.createCriteria()
                    .andIdEqualTo(pageDo.getId());
        }
        if (pageDo.getAccountId() != null) {
            // 充值账号
            example.createCriteria()
                    .andAccountIdEqualTo(pageDo.getAccountId());
        }
        if (pageDo.getOrderType() != null) {
            // 订单类型
            example.createCriteria()
                    .andOrderTypeEqualTo(pageDo.getOrderType());
        }
        if (pageDo.getOrderMethod() != null) {
            // 充值方式
            example.createCriteria()
                    .andOrderMethodEqualTo(pageDo.getOrderMethod());
        }
        if (pageDo.getStatus() != null) {
            // 订单状态
            example.createCriteria()
                    .andStatusEqualTo(pageDo.getStatus());
        }
        example.setOrderByClause("create_time DESC");
        PageHelper.startPage(pageDo.getPage(), pageDo.getRows());
        return (Page<OrderInfoDO>) orderInfoDOMapper.selectByExample(example);
    }

    /**
     * 创建新订单(初始 未支付)
     *
     * @param orderInfoDO 订单信息
     * @return 系统订单号(主键)
     */
    @Override
    public long createNewOrder(OrderInfoDO orderInfoDO) {
        orderInfoDO.setId(OrderUtil.getOrderId());
        orderInfoDO.setStatus(OrderStatus.INIT);
        orderInfoDO.setCreateTime(new Date());
        log.info("即将落库新订单:{}", JSON.toJSONString(orderInfoDO));
        orderInfoDOMapper.insertSelective(orderInfoDO);
        return orderInfoDO.getId();
    }

    /**
     * 更新订单信息
     *
     * @param orderInfoDO 订单信息
     * @return 影响条数
     */
    @Override
    public int updateOrder(OrderInfoDO orderInfoDO) {
        if (orderInfoDO.getId() == null) {
            log.warn("输入的id为空值: {}", orderInfoDO.getId());
        }
        orderInfoDO.setUpdateTime(new Date());
        return orderInfoDOMapper.updateByPrimaryKeySelective(orderInfoDO);
    }
}
