package com.zty.pay.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.zty.framework.exception.BusinessException;
import com.zty.framework.exception.ParamCheckException;
import com.zty.pay.DO.OrderInfoDO;
import com.zty.pay.config.AlipayConfig;
import com.zty.pay.constant.OrderStatus;
import com.zty.pay.request.AlipaySyncNotifyRequest;
import com.zty.pay.service.PayOrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thlws.payment.AlipayClient;
import org.thlws.payment.alipay.core.AlipayCore;
import org.thlws.payment.alipay.entity.request.AlipayMobileSiteRequest;
import org.thlws.payment.alipay.entity.request.AlipayRefundRequest;
import org.thlws.payment.alipay.entity.request.AlipayTradeRequest;
import org.thlws.payment.alipay.entity.request.AlipayWebSiteRequest;
import org.thlws.payment.alipay.entity.response.AlipayQueryResponse;
import org.thlws.payment.alipay.entity.response.AlipayRefundResponse;
import org.thlws.payment.alipay.entity.response.AlipayTradeResponse;

/**
 * 支付宝支付示例.
 * FIXME 示例中 [手机+电脑] 网站支付暂不可用,我这里没有进行相关配置而已。
 * @author HanleyTang
 * @date 2018 -12-02
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {

    private static final Logger log = LoggerFactory.getLogger(AlipayController.class);

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private PayOrderService payOrderService;

    /**
     * 支付宝付款测试页.
     *
     * @return the string
     */
    @RequestMapping("/index")
    public String index(){
        return "alipay";
    }

    /**
     * 当面付.
     * 用于线下POS，线上一般不会这么用
     *
     * @param amt     the amt
     * @param barcode the barcode
     * @return the object
     */
    @RequestMapping(value = "/pay/f2f",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object payFaceToFace(@RequestParam(name = "amt")String amt,
                                @RequestParam(name = "barcode")String barcode){
        try {
            AlipayCore.ClientBuilder clientBuilder = new AlipayCore.ClientBuilder();
            AlipayCore alipayCore = clientBuilder.setAlipayPublicKey(alipayConfig.getAlipay_public_key())
                    .setAppId(alipayConfig.getAppid())
                    .setPrivateKey(alipayConfig.getPrivate_key())
                    .setSignType(AlipayConstants.SIGN_TYPE_RSA2).build();

            AlipayTradeRequest request = new AlipayTradeRequest();
            request.setTotalAmount(amt);
            request.setStoreId("myStoreId001");
            request.setOperatorId("007");
            request.setAuthCode(barcode);
            request.setOutTradeNo(System.currentTimeMillis()+"");
            request.setSubject("测试当面付");
            AlipayTradeResponse response = AlipayClient.pay(request,alipayCore);
            return response;
        } catch (Exception e) {
            log.error("当面付失败",e);
            return e;
        }

    }

    /**
     * 手机网站支付.
     * 在手机浏览器打开(不能在微信中)完成付款动作.
     *
     * @param amt      the amt
     * @param modelMap the model map
     * @return the string
     */
    @RequestMapping("/pay/mobile")
    public String payInMobileSite(@RequestParam(name = "amt",defaultValue = "0.01")String amt,
                                  ModelMap modelMap){

        try {
            AlipayCore.ClientBuilder clientBuilder = new AlipayCore.ClientBuilder();
            AlipayCore alipayCore = clientBuilder.setAlipayPublicKey(alipayConfig.getAlipay_public_key())
                    .setAppId(alipayConfig.getAppid())
                    .setPrivateKey(alipayConfig.getPrivate_key())
                    .setSignType(AlipayConstants.SIGN_TYPE_RSA2).build();

            AlipayMobileSiteRequest request = new AlipayMobileSiteRequest();
            request.setNotifyUrl(null);

            //http://你的同步处理地址/alipay/alipay_sync_mobile
            request.setReturnUrl("http://39.105.94.70:7171/alipay/alipay_sync_mobile");
            AlipayMobileSiteRequest.BizContent bizContent = new AlipayMobileSiteRequest.BizContent();
            bizContent.setTotalAmount(amt);
            bizContent.setSubject("测试H5(手机网页)支付");
//            bizContent.setSeller_id(partner_id);
            bizContent.setOutTradeNo(System.currentTimeMillis()+"");
            request.setBizContent(bizContent);
            String html = AlipayClient.payInMobileSite(request,alipayCore);
            modelMap.addAttribute("form", html);
        } catch (Exception e) {
            log.error("手机网站支付失败",e);
        }
        return "alipay/alipay_mobile";
    }

    /**
     * 支付宝支付 异步通知
     * @return
     */
    @ResponseBody
    @PostMapping("/alipay_async_notify")
    public String alipayAsyncNotify(AlipaySyncNotifyRequest request) throws IOException {
        // 编码格式
        String charset = StringUtils.isBlank(request.getCharset()) ? "utf-8" : request.getCharset();
        // 支付宝公钥
        String alipaypublicKey = alipayConfig.getAlipay_public_key();
        // 签名方式
        String sign_type = StringUtils.isBlank(request.getSign_type()) ? "RSA2" : request.getSign_type();
        // 入参转Map
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(request);
        Map<String, String> paramStrMap = jsonObject.getInnerMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> String.valueOf(v)));
        //验签
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(paramStrMap, alipaypublicKey, charset, sign_type);
        } catch (AlipayApiException e) {
            log.error("验签异常, ", e);
            throw new ParamCheckException("check sign execption");
        }
        if(!signVerified){
            throw new ParamCheckException("check sign fail");
        }
        // 验签成功后，更新本地订单状态
        OrderInfoDO localOrder = null;
        try {
            OrderInfoDO queryOrder = new OrderInfoDO();
            queryOrder.setId(Long.valueOf(request.getOut_trade_no()));
            localOrder = payOrderService.checkAndGet(queryOrder);
            if (localOrder == null) {
                throw new ParamCheckException("订单不存在", "out_trade_no", request.getOut_trade_no());
            }
            if (OrderStatus.isFinalStatus(localOrder.getStatus())) {
                throw new ParamCheckException("订单已是支付完成状态"+localOrder.getStatus());
            }
            // 修改订单状态
            localOrder.setBeforeStatus(localOrder.getStatus());
            localOrder.setStatus(OrderStatus.fromTradeStatus(request.getTrade_status()));
            localOrder.setUpdateTime(new Date());
            int row = payOrderService.updateOrder(localOrder);
            if (row == 0) {
                throw new BusinessException("500", "修改订单状态失败");
            }
        } catch (Exception e) {
            log.error("本地订单处理异常, ",e);
            throw new BusinessException("500", e.getMessage());
        }
        return "success";
    }

    /**
     * 支付宝支付 同步通知
     * @return
     */
    @GetMapping("/alipay_sync_notify")
    public String alipaySyncNotify(AlipaySyncNotifyRequest request,
                                   @RequestParam(name = "returnUrl", defaultValue = "returnUrl") String returnUrl,
                                    HttpServletResponse response) throws IOException {
        // 编码格式
        String charset = StringUtils.isBlank(request.getCharset()) ? "utf-8" : request.getCharset();
        // 支付宝公钥
        String alipaypublicKey = alipayConfig.getAlipay_public_key();
        // 签名方式
        String sign_type = StringUtils.isBlank(request.getSign_type()) ? "RSA2" : request.getSign_type();
        // 入参转Map
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(request);
        Map<String, String> paramStrMap = jsonObject.getInnerMap().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, v -> String.valueOf(v)));
        //验签
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(paramStrMap, alipaypublicKey, charset, sign_type);
        } catch (AlipayApiException e) {
            log.error("验签异常, ", e);
            throw new ParamCheckException("check sign execption");
        }
        if(!signVerified){
            throw new ParamCheckException("check sign fail");
        }
        // 验签成功后，更新本地订单状态
        OrderInfoDO localOrder = null;
        try {
            OrderInfoDO queryOrder = new OrderInfoDO();
            queryOrder.setId(Long.valueOf(request.getOut_trade_no()));
            localOrder = payOrderService.checkAndGet(queryOrder);
            if (localOrder == null) {
                throw new ParamCheckException("订单不存在", "out_trade_no", request.getOut_trade_no());
            }
            if (OrderStatus.isFinalStatus(localOrder.getStatus())) {
                throw new ParamCheckException("订单已是支付完成状态"+localOrder.getStatus());
            }
            // 修改订单状态
            localOrder.setBeforeStatus(localOrder.getStatus());
            localOrder.setStatus(OrderStatus.fromTradeStatus(request.getTrade_status()));
            localOrder.setUpdateTime(new Date());
            int row = payOrderService.updateOrder(localOrder);
            if (row == 0) {
                throw new BusinessException("500", "修改订单状态失败");
            }
        } catch (Exception e) {
            log.error("本地订单处理异常, ",e);
            response.sendRedirect(returnUrl);
            return "alipay/alipay_sync_web";
        }
        return "alipay/alipay_sync_web";
    }

    /**
     * 电脑网站支付.
     * @param amt 金额(元)
     * @param orderId 订单号
     * @param subject 交易主题(thl)
     * @param body 内容(thl)
     * @param returnUrl 支付完成后的跳转地址
     * @return
     */
    @RequestMapping("/pay/web")
    public String payInWebSite(@RequestParam(name = "amt",defaultValue = "0.01")String amt,
                               @RequestParam(name = "orderId",defaultValue = "0")String orderId,
                               @RequestParam(name = "subject",defaultValue = "subject")String subject,
                               @RequestParam(name = "body",defaultValue = "body")String body,
                               @RequestParam(name = "returnUrl",defaultValue = "returnUrl")String returnUrl,
                               ModelMap modelMap, HttpServletResponse response) throws IOException {
        OrderInfoDO localOrder = null;
        try {
            // 检查本地订单状态
            OrderInfoDO queryOrder = new OrderInfoDO();
            queryOrder.setId(Long.valueOf(orderId));
            localOrder = payOrderService.checkAndGet(queryOrder);
            if (localOrder == null) {
                throw new ParamCheckException("订单不存在", "orderId", orderId);
            }
            if (!OrderStatus.canPay(localOrder.getStatus())) {
                throw new ParamCheckException("订单不是可支付状态"+localOrder.getStatus());
            }
            // 修改订单状态
            localOrder.setBeforeStatus(localOrder.getStatus());
            localOrder.setStatus(OrderStatus.DEALING);
            localOrder.setUpdateTime(new Date());
            int row = payOrderService.updateOrder(localOrder);
            if (row == 0) {
                throw new BusinessException("500", "修改订单状态失败");
            }
        } catch (Exception e) {
            log.error("本地订单处理异常, ",e);
            response.sendRedirect(returnUrl);
            return "alipay/alipay_web";
        }

        try {
            AlipayCore.ClientBuilder clientBuilder = new AlipayCore.ClientBuilder();
            AlipayCore alipayCore = clientBuilder.setAlipayPublicKey(alipayConfig.getAlipay_public_key())
                    .setAppId(alipayConfig.getAppid())
                    .setPrivateKey(alipayConfig.getPrivate_key())
                    .setSignType(AlipayConstants.SIGN_TYPE_RSA2).build();

            AlipayWebSiteRequest request = new AlipayWebSiteRequest();

            // 设置异步通知地址
            request.setNotifyUrl(alipayConfig.getNotifyUrl());

            // 设置同步跳转地址
            request.setReturnUrl(alipayConfig.getRetrunUrl() + "?returnUrl=" + returnUrl);

            AlipayWebSiteRequest.BizContent bizContent = new AlipayWebSiteRequest.BizContent();
            bizContent.setTotalAmount(amt);
            bizContent.setSubject(subject);
            bizContent.setBody(body);
            bizContent.setOutTradeNo(orderId);
            request.setBizContent(bizContent);
            String html = AlipayClient.payInWebSite(request,alipayCore);
            modelMap.addAttribute("form", html);
        } catch (Exception e) {
            log.error("电脑网站支付失败",e);
            if (null != localOrder) {
                localOrder.setStatus(OrderStatus.FAIL);
                localOrder.setRemarks(e.getMessage());
                localOrder.setUpdateTime(new Date());
                payOrderService.updateOrder(localOrder);
            }
            response.sendRedirect(returnUrl);
            return "alipay/alipay_web";
        }
        return "alipay/alipay_web";
    }

    /**
     * 查询,适用所有付款方式.
     *
     * @param outTradeNo the out trade no
     * @return the object
     */
    @RequestMapping(value = "/query",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object query(@RequestParam(name = "out_trade_no")String outTradeNo){

        try {
            AlipayCore.ClientBuilder clientBuilder = new AlipayCore.ClientBuilder();
            AlipayCore alipayCore = clientBuilder.setAlipayPublicKey(alipayConfig.getAlipay_public_key())
                    .setAppId(alipayConfig.getAppid())
                    .setPrivateKey(alipayConfig.getPrivate_key())
                    .setSignType(AlipayConstants.SIGN_TYPE_RSA2).build();


            AlipayQueryResponse response = AlipayClient.query(outTradeNo, alipayCore);
            return response;
        } catch (Exception e) {
            log.error("支付宝查询失败.",e);
            return e;
        }
    }


    @RequestMapping(value = "/refund",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Object refund(@RequestParam(name = "amt")String amt,
                         @RequestParam(name = "out_trade_no")String outTradeNo){

        try {
            AlipayCore.ClientBuilder clientBuilder = new AlipayCore.ClientBuilder();
            AlipayCore alipayCore = clientBuilder.setAlipayPublicKey(alipayConfig.getAlipay_public_key())
                    .setAppId(alipayConfig.getAppid())
                    .setPrivateKey(alipayConfig.getPrivate_key())
                    .setSignType(AlipayConstants.SIGN_TYPE_RSA2).build();

            AlipayRefundRequest request = new AlipayRefundRequest();
            request.setOutTradeNo(outTradeNo);
            request.setRefundAmount(amt);
            request.setRefundReason("测试退款");
            request.setOutRequestNo(System.currentTimeMillis() + "");
            AlipayRefundResponse response = AlipayClient.refund(request, alipayCore);
            return response;
        } catch (Exception e) {
            log.error("支付宝退款失败.",e);
            return e;
        }
    }


    /**
     * 手机网站支付同步跳转页面
     *
     * @param request the request
     * @return the string
     */
    @RequestMapping("/alipay_sync_mobile")
    public String sync_mobile(HttpServletRequest request){

        System.out.println("================sync_mobile===================");
        Enumeration<String> its = request.getParameterNames();
        while (its.hasMoreElements()){
            String name = its.nextElement();
            String value = request.getParameter(name);
            log.info(name+"="+value);
        }
        System.out.println("================sync_mobile===================");
        return "alipay/alipay_sync_mobile";
    }


    /**
     * 电脑网站支付同步跳转页面.
     */
    @RequestMapping("/alipay_sync_web")
    public String sync_web(HttpServletRequest request){
        System.out.println("==============sync_web=====================");
        Enumeration<String> its = request.getParameterNames();
        while (its.hasMoreElements()){
            String name = its.nextElement();
            String value = request.getParameter(name);
            log.info(name+"="+value);
        }
        System.out.println("===============sync_web====================");
        return "alipay/alipay_sync_web";
    }

}
