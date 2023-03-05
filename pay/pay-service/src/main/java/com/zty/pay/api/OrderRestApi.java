package com.zty.pay.api;

import com.zty.framework.dto.ResultDTO;
import com.zty.pay.DO.OrderInfoDO;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 去支付中心查询订单的 REST API
 * @author: tianyi.zeng
 * @create: 14/1/2022 - 下午 5:53
 */
public interface OrderRestApi {

    /**
     * 查询订单
     * @param postBody 报文体
     */
    @POST("order/query")
    @FormUrlEncoded
    public Call<ResultDTO<OrderInfoDO>> query(@Field("orderId") String orderId);
}
