//package com.zty.web.controller;
//
//import com.zty.bo.api.PcAuthorizeApi;
//import com.zty.bo.api.WxAuthorizeApi;
//import com.zty.common.config.WxKeyConfig;
//import com.zty.framework.dto.ResultDTO;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author tianyi
// * @date 2020-05-02 18:30
// */
//@RestController
//@RequestMapping("/wx")
//public class WxController {
//
//    @Autowired
//    private WxAuthorizeApi wxAuthorizeApi;
//
//    @Autowired
//    private PcAuthorizeApi pcAuthorizeApi;
//
//    @GetMapping("/wx_checkOpenidExistAndReturnAccount")
//    public ResultDTO wx_checkOpenidExistAndReturnAccount(@RequestParam("code")String code, @RequestParam("state")String state){
//        if (StringUtils.isBlank(code)){
//            return ResultDTO.error(403, "code参数为空，用户没有同意授权");
//        }
//        return wxAuthorizeApi.checkOpenidExistAndReturnAccount(code);
//    }
//
//    @GetMapping("/registerByOpenidAndReturnAccount")
//    public ResultDTO registerByOpenidAndReturnAccount(@RequestParam("code")String code, @RequestParam("state")String state){
//        if (StringUtils.isBlank(code)){
//            return ResultDTO.error(403, "code参数为空，用户没有同意授权");
//        }
//        return wxAuthorizeApi.registerByOpenidAndReturnAccount(code);
//    }
//
//    @GetMapping("/refeshTokenAndReturnAccount")
//    public ResultDTO refeshTokenAndReturnAccount(@RequestParam("code")String code, @RequestParam("state")String state){
//        if (StringUtils.isBlank(code)){
//            return ResultDTO.error(403, "code参数为空，用户没有同意授权");
//        }
//        return wxAuthorizeApi.refeshTokenAndReturnAccount(code);
//    }
//
//    @GetMapping("/pc_checkOpenidExistAndReturnAccount")
//    public ResultDTO pc_checkOpenidExistAndReturnAccount(@RequestParam("code")String code, @RequestParam("state")String state){
//        if (StringUtils.isBlank(code)){
//            return ResultDTO.error(403, "code参数为空，用户没有同意授权");
//        }
//        return pcAuthorizeApi.checkOpenidExistAndReturnAccount(code);
//    }
//}
