//package com.zty.msg.service.impl;
//
//import java.net.SocketTimeoutException;
//import java.text.MessageFormat;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.zty.msg.dto.MobileReqInfoDTO;
//import com.zty.msg.dto.MobileRespInfoDTO;
//import com.zty.msg.enums.SmsStatusEnum;
//import com.zty.msg.request.SmsSendRequest;
//import com.zty.msg.response.SmsSendResponse;
//import com.zty.msg.service.SmsSendService;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.http.Header;
//import org.apache.http.message.BasicHeader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
///**
// * 容联云通讯短信发送类
// * @author devwang
// * @date 2019/12/9 9:45
// */
//@Service("cloopenSmsSendServiceImpl")
//public class CloopenSmsSendServiceImpl implements SmsSendService {
//
//    private Logger logger = LoggerFactory.getLogger(CloopenSmsSendServiceImpl.class);
//
//    private static final String SERVICE_NAME = "sms.cloopen";
//
//    /**容联云发送成功第三方状态码*/
//    private static final String SUCCESS = "000000";
//
//    /**容联云状态码字段名*/
//    private static final String STATUS_CODE = "statusCode";
//
//    /**容联云状态描述字段名*/
//    private static final String STATUS_MSG = "statusMsg";
//
//    @Override
//    public String getServiceName() {
//        return SERVICE_NAME;
//    }
//
//    @Override
//    public SmsSendResponse sendOne(SmsSendRequest request) {
//        String mobile = request.getMobileReqInfoDTOList().get(0).getMobile();
//        return this.send(mobile, request);
//    }
//
//    @Override
//    public SmsSendResponse sendBatch(SmsSendRequest request) {
//        String mobiles = this.buildPhones(request.getMobileReqInfoDTOList());
//        return this.send(mobiles, request);
//    }
//
//    private SmsSendResponse send(String mobile, SmsSendRequest request) {
//        // 解析第三方通道参数
//        JSONObject configJson = JSON.parseObject(request.getConfigJson());
//        // url处理
//        String url = request.getUrl();
//        String accountId = configJson.getString("accountId");
//        String authToken = configJson.getString("authToken");
//        String sign = calcCloopenSign(accountId, authToken);
//        String concreteUrl = MessageFormat.format(url, accountId, sign);
//        // 请求头处理
//        Header[] headers = new Header[3];
//        headers[0] = new BasicHeader("Accept", "application/json");
//        headers[1] = new BasicHeader("Content-Type", "application/json;charset=utf-8");
//        headers[2] = new BasicHeader("Authorization", calcAuthorization(accountId));
//        // 参数body处理
//        Map<String, String> params = new HashMap<>(8);
//        params.put("to", mobile);
//        params.put("appId", configJson.getString("appId"));
//        params.put("reqId", request.getBatchId().toString());
//        params.put("templateId", request.getTemplateId().toString());
//        List<String> paramList = request.getParamList();
//        if (paramList != null && !paramList.isEmpty()) {
//            params.put("datas", new JSONArray(paramList.stream().map(param -> (Object)param).collect(Collectors.toList())).toJSONString());
//        }
//        //超时处理
//        Integer timeout = request.getTimeout();
//        //发送短信
//        logger.info("容联云通道短信发送参数：\r\nurl:{}\r\nheader:{}\r\nbody:{}\r\nsocketTimeout:{}ms", concreteUrl, Arrays.toString(headers), JSONObject.toJSONString(params), timeout);
//        String result;
//        try {
//            result = HttpHelper.postJson(concreteUrl, headers, request.getProxyHost(), request.getProxyPort(), JSONObject.toJSONString(params), timeout, null);
//        } catch (SocketTimeoutException e) {
//            logger.error("容联云通道短信发送超时：" + e);
//            SmsSendResponse responseAo = new SmsSendResponse();
//            responseAo.setOutStatus(ThirdEZxceptionInfo.SOCKET_TIMEOUT.getCode());
//            responseAo.setOutDescription(ThirdExceptionInfo.SOCKET_TIMEOUT.getMessage());
//            responseAo.setStatus(PublicExceptionInfo.THIRD_OVERTIME_EXCEPTION.getCode());
//            return responseAo;
//        }
//        logger.info("容联云通道短信发送结果：{}", result);
//        JSONObject resultJson = JSONObject.parseObject(result);
//        SmsSendResponse responseAo = new SmsSendResponse();
//        responseAo.setBatchId(request.getBatchId());
//        if (SUCCESS.equals(resultJson.getString(STATUS_CODE))) {
//            responseAo.setStatus(PublicExceptionInfo.SUCCESS.getCode());
//            responseAo.setDescription(PublicExceptionInfo.SUCCESS.getMessage());
//            responseAo.setOutDescription(PublicExceptionInfo.SUCCESS.getMessage());
//            List<MobileRespInfoDTO> mobileRespInfoAos = request.getMobileReqInfoDTOList().stream().map(mobileInfo -> {
//                MobileRespInfoDTO mobileRespInfoAo = new MobileRespInfoDTO();
//                mobileRespInfoAo.setMobile(mobileInfo.getMobile());
//                mobileRespInfoAo.setSmsRecordId(mobileInfo.getSmsRecordId());
//                mobileRespInfoAo.setStatus(SmsStatusEnum.SUCCESS);
//                return mobileRespInfoAo;
//            }).collect(Collectors.toList());
//            responseAo.setMobileRespInfoDTOList(mobileRespInfoAos);
//        } else {
//            responseAo.setStatus(PublicExceptionInfo.FAIL.getCode());
//            responseAo.setDescription(PublicExceptionInfo.FAIL.getMessage());
//            responseAo.setOutDescription(Optional.ofNullable(resultJson.getString(STATUS_MSG)).orElse("发送失败"));
//        }
//        responseAo.setOutStatus(resultJson.getString(STATUS_CODE));
//        return responseAo;
//    }
//
//    /**
//     * 容联云通讯签名生成
//     * @author devwang
//     * @param accountId 账号ID
//     * @param authToken 秘钥
//     * @date 2019/12/9 10:08
//     * @return java.lang.String 签名
//     */
//    public static String calcCloopenSign(String accountId, String authToken) {
//        return DigestUtils.md5Hex(accountId + authToken + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
//    }
//
//    /**
//     * 容联云通讯认证参数生成
//     * @author devwang
//     * @param accountId 账号ID
//     * @date 2019/12/9 10:08
//     * @return java.lang.String 认证头
//     */
//    public static String calcAuthorization(String accountId) {
//        return Base64.getEncoder().encodeToString((accountId + ":" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())).getBytes());
//    }
//
//    /**
//     * 组装手机号列表
//     * @author devwang
//     * @param phones 手机号参数list
//     * @date 2019/12/9 10:44
//     * @return java.lang.String 手机号列表
//     */
//    private String buildPhones(List<MobileReqInfoDTO> phones){
//        return phones.stream().map(MobileReqInfoDTO::getMobile).reduce((v1, v2) -> v1 + "," + v2).orElseThrow(() -> new BusinessException(PublicExceptionInfo.PARAMETER_NOT_NULL_EXCEPTION));
//    }
//}
