package com.zl.mall.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

/**
 * @author zhongliang
 */
public class JwtUtil {

    public static  Map<String, Claim>  parse(String token){
        DecodedJWT decode = JWT.decode(token);
        Map<String, Claim> claims = decode.getClaims();
        return  claims;
    }

    public static void verify(String token){
        JWTVerifier build = JWT.require(Algorithm.HMAC256("cjs")).build();
        DecodedJWT verify = build.verify(token);
    }
    
    public static void main(String[] args) {
		Map<String, Claim> parse = parse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJxcXFxIiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTY4NDg2MzM3MSwidXNlcklkIjoiYmYyNWE3M2IyYjQ1NDk2YjhjNjIwMDZjODIyNGY5OWMiLCJqdGkiOiI5N2FkZWRlOC00NDU1LTQwMjgtYmQzYy05MDZjNWM4MTFhNWYiLCJjbGllbnRfaWQiOiJ3ZWIiLCJ1c2VybmFtZSI6InFxcXEifQ.MVhsxU2XRtA2gEwUh86SUVaROP2b_zbXxp8C-VWS8Y8");
		Claim claim = parse.get("userId");
		String asString = claim.asString();
		System.out.println(asString);
	}

}
