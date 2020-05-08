package com.zty.bo.service.impl;

import com.zty.bo.service.WxService;
import com.zty.common.ao.CheckTokenReqAO;
import com.zty.common.ao.CreateTokenReqAO;
import com.zty.common.ao.GetWxUserInfoReqAO;
import com.zty.common.ao.RefreshTokenReqAO;
import com.zty.common.dto.WxResultDTO;
import org.springframework.stereotype.Service;

/**
 * TODO 实现发送请求调微信接口
 * @author tianyi
 * @date 2020-05-02 19:48
 */
@Service
public class WxServiceImpl implements WxService {

    @Override
    public WxResultDTO checkToken(CheckTokenReqAO checkTokenReqAO) {
        return null;
    }

    @Override
    public WxResultDTO refeshToken(RefreshTokenReqAO refreshTokenReqAO) {
        return null;
    }

    @Override
    public WxResultDTO getWxUserInfo(GetWxUserInfoReqAO getWxUserInfoReqAO) {
        return null;
    }

    @Override
    public WxResultDTO createToken(CreateTokenReqAO createTokenReqAO) {
        return null;
    }
}
