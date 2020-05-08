package com.zty.bo.service;

import com.zty.common.ao.CheckTokenReqAO;
import com.zty.common.ao.CreateTokenReqAO;
import com.zty.common.ao.GetWxUserInfoReqAO;
import com.zty.common.ao.RefreshTokenReqAO;
import com.zty.common.dto.WxResultDTO;

/**
 * 处理微信相关的业务
 * @author tianyi
 * @date 2020-05-02 18:38
 */
public interface WxService {

    public WxResultDTO checkToken(CheckTokenReqAO checkTokenReqAO);

    public WxResultDTO refeshToken(RefreshTokenReqAO refreshTokenReqAO);

    public WxResultDTO getWxUserInfo(GetWxUserInfoReqAO getWxUserInfoReqAO);

    public WxResultDTO createToken(CreateTokenReqAO createTokenReqAO);
}
