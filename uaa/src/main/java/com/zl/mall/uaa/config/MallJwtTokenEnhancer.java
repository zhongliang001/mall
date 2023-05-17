package com.zl.mall.uaa.config;

import java.util.Map;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.zl.mall.common.utils.ObjectUtil;
import com.zl.mall.user.userauth.dto.UserAuthDetails;

/**
 * @author zhongliang
 */
@Configuration
public class MallJwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    	UserAuthDetails principal = (UserAuthDetails)authentication.getPrincipal();
        principal.setPassword(null);
        Map<String, Object> info = ObjectUtil.objectToMap(principal);
        //设置附加信息
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
