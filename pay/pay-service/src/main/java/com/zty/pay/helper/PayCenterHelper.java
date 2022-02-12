package com.zty.pay.helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;

import cn.hutool.core.net.URLEncoder;
import com.zty.framework.dto.ResultDTO;
import com.zty.framework.exception.NetworkException;
import com.zty.framework.third.converter.StringConverterFactory;
import com.zty.pay.DO.OrderInfoDO;
import com.zty.pay.api.OrderRestApi;
import com.zty.pay.config.PayCenterConfig;
import javax.annotation.PostConstruct;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: tianyi.zeng
 * @create: 8/2/2022 - 下午 10:54
 */
@Component
public class PayCenterHelper {

    private final static Logger logger = LoggerFactory.getLogger(PayCenterHelper.class);

    @Autowired
    private PayCenterConfig payCenterConfig;

    private OrderRestApi orderRestApi;

    @PostConstruct
    public void init() {
        // 创建 OkHttp 客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // 修改连接超时时间
                .connectTimeout(Duration.ofSeconds(5))
                // 修改写超时时间
                .writeTimeout(Duration.ofSeconds(10))
                // 修改读超时时间
                .readTimeout(Duration.ofSeconds(15))
                // 设置日志打印拦截器
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        // 获取原请求
                        Request original = chain.request();
                        // 打印请求内容
                        logger.info("即将{}请求:{}, 请求头:{}",
                                original.method(),
                                original.url().toString(),
                                original.headers().toString());
                        // 继续执行原请求
                        return chain.proceed(original);
                    }
                })
                .build();
        // 创建Retrofit实例
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Objects.requireNonNull(payCenterConfig.getBaseUrl()))
                // 添加格式转换器
                .addConverterFactory(new StringConverterFactory())   //响应报文为空的场景，其他转换器会出现异常
                .addConverterFactory(GsonConverterFactory.create())  // 解析
                .build();
        orderRestApi = retrofit.create(OrderRestApi.class);
    }

    /**
     * 获取从页面跳转至支付中心完成支付的url
     * @param amt 支付金额
     * @param orderId 本地订单号
     * @param subject 交易主体
     * @param body
     * @param returnUrl 回调url
     * @return
     */
    public String getAlipayWebpayUrl(String amt,
                                     String orderId,
                                     String subject,
                                     String body,
                                     String returnUrl) {
        StringBuilder sb = new StringBuilder( );
        sb.append("&amt=" + URLEncoder.QUERY.encode(amt, StandardCharsets.UTF_8));
        sb.append("&orderId=" + URLEncoder.QUERY.encode(orderId, StandardCharsets.UTF_8));
        sb.append("&subject=" + URLEncoder.QUERY.encode(subject, StandardCharsets.UTF_8));
        sb.append("&body=" + URLEncoder.QUERY.encode(body, StandardCharsets.UTF_8));
        sb.append("&returnUrl=" + URLEncoder.QUERY.encode(returnUrl == null ? "" : returnUrl, StandardCharsets.UTF_8));
        return payCenterConfig.getAlipayWebpay() + sb.toString().replaceFirst("&", "?");
    }

    public ResultDTO<OrderInfoDO> queryOrder(OrderInfoDO orderInfoDO) {
        logger.info("即将: curl -X POST --H 'Content-Type: application/x-www-form-urlencoded' {}order/query -d 'orderId={}'", payCenterConfig.getBaseUrl(), orderInfoDO.getIdValue());
        Call<ResultDTO<OrderInfoDO>> call = orderRestApi.query(String.valueOf(orderInfoDO.getIdValue()));
        try {
            Response<ResultDTO<OrderInfoDO>> response = call.execute();
            int statusCode = response.code();
            logger.info("响应code：{}", statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                logger.info("响应body：{}", response.body());
                return response.body();
            } else {
                logger.error("响应内容：{}", response.body());
                throw new NetworkException("支付中心查订单失败, httpcode非200");
            }
        } catch (IOException e) {
            logger.error("请求支付中心queryOrder失败, 网络异常，", e);
            throw new NetworkException("创建任务失败, 网络异常" + e.getMessage());
        }
    }

}
