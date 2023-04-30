package com.jobseeckerstudio.user.service.mapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.service.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
import org.springframework.beans.factory.annotation.Value;

public class UserServiceMapper {

    @Value("${jwt.secret}")
    private static String SECRET;
    @Value("${jwt.token_prefix}")
    private static String TOKEN_PREFIX;

    private static final String userKey  = "userKey";

    public static FindUserWhenSocialLoginRequest toFindUserWhenSocialLoginRequest(SocialUserInfo socialUserInfo) {
        return FindUserWhenSocialLoginRequest.builder()
            .userKey(socialUserInfo.getProvider()+"_"+ socialUserInfo.getUserKey())
            .build();
    }

    public static GetEmailRequest toGetEmailRequest(JwtToken jwtToken) {
        return GetEmailRequest.builder()
            .userKey(parsingJwt(jwtToken.getJwtToken().replace(TOKEN_PREFIX, ""), userKey))
            .build();
    }


    public static GetEmailResponse toGetEmailResponse(String email) {
        return GetEmailResponse.builder()
            .email(email)
            .build();
    }


        private static String parsingJwt(String jwtToken, String key) {
            return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(jwtToken).getClaim(key).asString();
    }


}
