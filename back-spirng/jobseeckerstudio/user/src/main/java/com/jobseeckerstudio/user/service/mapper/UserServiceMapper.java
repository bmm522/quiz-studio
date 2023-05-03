package com.jobseeckerstudio.user.service.mapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.exception.UnauthorizedException;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.service.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;

public class UserServiceMapper {

    private static final String userKey  = "userKey";

    public static FindUserWhenSocialLoginRequest toFindUserWhenSocialLoginRequest(SocialUserInfo socialUserInfo) {
        return FindUserWhenSocialLoginRequest.builder()
            .userKey(socialUserInfo.getProvider()+"_"+ socialUserInfo.getUserKey())
            .build();
    }

    public static GetEmailRequest toGetEmailRequest(JwtToken jwtToken) {
        return GetEmailRequest.builder()
            .userKey(parsingJwt(jwtToken.getJwtToken().replace(JwtProperties.TOKEN_PREFIX, ""), userKey))
            .build();
    }


    public static GetEmailResponse toGetEmailResponse(String email) {
        return GetEmailResponse.builder()
            .email(email)
            .build();
    }


        private static String parsingJwt(String jwtToken, String key) {
            try {
                return  JWT.require(Algorithm.HMAC256(JwtProperties.SECRET)).build().verify(jwtToken).getClaim(key).asString();
            } catch (Exception e) {
                throw new UnauthorizedException("잘못된 토큰 정보입니다.");
        }

    }


}
