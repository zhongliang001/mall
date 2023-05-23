package com.zl.mall.common.utils;

import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author zhongliang
 */
public class JwtUtil {

    public static  Map<String, Claim>  parse(String token){
        DecodedJWT decode = JWT.decode(token);
        Map<String, Claim> claims = decode.getClaims();
        return  claims;
    }

}
