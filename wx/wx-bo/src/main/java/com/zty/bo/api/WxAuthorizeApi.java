//package com.zty.bo.api;
//
//import com.zty.common.service.UserService;
//import com.zty.bo.service.WxService;
//import com.zty.common.DO.UserInfoDO;
//import com.zty.common.ao.CreateTokenReqAO;
//import com.zty.common.ao.GetWxUserInfoReqAO;
//import com.zty.common.config.WxKeyConfig;
//import com.zty.common.dto.UserInfoDTO;
//import com.zty.common.dto.WxResultDTO;
//import com.zty.common.dto.WxTokenDTO;
//import com.zty.common.dto.WxUserInfoDTO;
//import com.zty.framework.dto.ResultDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.security.NoSuchAlgorithmException;
//
///**
// * @author tianyi
// * @date 2020-05-02 19:01
// */
//@Service
//public class WxAuthorizeApi {
//
//    private static final Logger log = LoggerFactory.getLogger(WxAuthorizeApi.class);
//
//    @Autowired
//    private WxService wxService;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private WxKeyConfig wxKeyConfig;
//
//    public ResultDTO checkOpenidExistAndReturnAccount(String code){
//        // 获取openid与access_token
//        CreateTokenReqAO createTokenReqAO = new CreateTokenReqAO();
//        createTokenReqAO.setAppid(wxKeyConfig.getAppid());
//        createTokenReqAO.setSecret(wxKeyConfig.getSecret());
//        createTokenReqAO.setCode(code);
//        WxResultDTO createResult = wxService.createToken(createTokenReqAO);
//        if (!createResult.isSuccess()){
//            return ResultDTO.error(444, "无法获取openid", createResult.getData());
//        }
//        WxTokenDTO wxTokenDTO = (WxTokenDTO) createResult.getData();
//        // 检查该openid是否存在对应用户
//        UserInfoDTO userInfoDTO = null;
//        try {
//            userInfoDTO = userService.checkByOpenid(wxTokenDTO.getOpenid());
//        } catch (IllegalAccessException e) {
//            log.error("反射生成UserInfoDTO时，字段权限非法",e);
//            return ResultDTO.error(500, "反射生成UserInfoDTO时，字段权限非法");
//        } catch (InstantiationException e) {
//            log.error("反射生成UserInfoDTO时，实例化失败",e);
//            return ResultDTO.error(500, "反射生成UserInfoDTO时，实例化失败");
//        }
//        if (userInfoDTO == null){
//            // 用户不存在，提示授权
//            return ResultDTO.error(888, "用户不存在，授权创建新用户");
//        }else{
//            // 用户存在，TODO 更新token
//            return ResultDTO.success(userInfoDTO);
//        }
//    }
//
//    public ResultDTO registerByOpenidAndReturnAccount(String code){
//        // 获取openid与access_token
//        CreateTokenReqAO createTokenReqAO = new CreateTokenReqAO();
//        createTokenReqAO.setAppid(wxKeyConfig.getAppid());
//        createTokenReqAO.setSecret(wxKeyConfig.getSecret());
//        createTokenReqAO.setCode(code);
//        WxResultDTO createResult = wxService.createToken(createTokenReqAO);
//        if (!createResult.isSuccess()){
//            return ResultDTO.error(444, "无法获取openid", createResult.getData());
//        }
//        WxTokenDTO wxTokenDTO = (WxTokenDTO) createResult.getData();
//        // 获取微信基本信息并创建新用户
//        GetWxUserInfoReqAO getWxUserInfoReqAO = new GetWxUserInfoReqAO();
//        getWxUserInfoReqAO.setAccess_token(wxTokenDTO.getAccess_token());
//        getWxUserInfoReqAO.setOpenid(wxTokenDTO.getOpenid());
//        WxResultDTO getInfoResult = wxService.getWxUserInfo(getWxUserInfoReqAO);
//        if (!getInfoResult.isSuccess()){
//            return ResultDTO.error(444, "无法获取用户基本信息", getInfoResult.getData());
//        }
//        // 创建用户
//        WxUserInfoDTO wxUserInfoDTO = (WxUserInfoDTO) getInfoResult.getData();
//        UserInfoDO newUser = new UserInfoDO();
//        newUser.setName(wxUserInfoDTO.getNickname());
//        newUser.setSex(wxUserInfoDTO.getSex());
//        newUser.setCountry(wxUserInfoDTO.getCountry());
//        newUser.setProvince(wxUserInfoDTO.getProvince());
//        newUser.setCity(wxUserInfoDTO.getCity());
//        newUser.setHeadPic(wxUserInfoDTO.getHeadimgurl());
//        newUser.setAccountKind((byte) 2);
//        newUser.setOpenid(wxTokenDTO.getOpenid());
//        UserInfoDTO userInfoDTO = null;
//        try {
//            userService.register(newUser);
//            userInfoDTO = userService.checkByOpenid(wxTokenDTO.getOpenid());
//        } catch (NoSuchAlgorithmException e) {
//            log.error("密码加密时出错，找不到该算法", e);
//            return ResultDTO.error(500, "创建账号时加密出错", e);
//        } catch (IllegalAccessException e) {
//            log.error("反射生成UserInfoDTO时，字段权限非法",e);
//            return ResultDTO.error(500, "反射生成UserInfoDTO时，字段权限非法");
//        } catch (InstantiationException e) {
//            log.error("反射生成UserInfoDTO时，实例化失败",e);
//            return ResultDTO.error(500, "反射生成UserInfoDTO时，实例化失败");
//        }
//        // TODO 创建token缓存
//        return ResultDTO.success(userInfoDTO);
//    }
//
//    public ResultDTO refeshTokenAndReturnAccount(String code){
//        // TODO 刷新缓存
//        return ResultDTO.success();
//    }
//
//}
