package com.zty.msg.service;

import com.zty.msg.request.SmsSendRequest;
import com.zty.msg.response.SmsSendResponse;

/**
 * 短信接口（第三方短信通道需实现该接口）
 * @author devwang
 * @date 2019/12/30 14:56
 */
public interface SmsSendService {

    /**
     * 获取服务名称
     * @return 服务名
     */
    String getServiceName();

    /**
     * 发送短信
     * @author devwang
     * @param smsSendRequest 短信发送参数
     * @date 2019/12/30 14:57
     * @return 短信发送响应结果
     */
    SmsSendResponse sendOne(SmsSendRequest smsSendRequest);

    /**
     * 发送短信
     * @author devwang
     * @param smsSendRequest 短信发送参数
     * @date 2019/12/30 14:57
     * @return 短信发送响应结果
     */
    SmsSendResponse sendBatch(SmsSendRequest smsSendRequest);
}
