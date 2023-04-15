package com.jobseeckerstudio.user.service.mapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.service.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
import org.springframework.stereotype.Component;

@Component
public class UserServiceMapper {


    private final String userKey  = "userKey";

    public FindUserWhenSocialLoginRequest toFindUserWhenSocialLoginRequest(SocialUserInfo socialUserInfo) {
        return FindUserWhenSocialLoginRequest.builder()
            .userKey(socialUserInfo.getProvider()+"_"+ socialUserInfo.getUserKey())
            .build();
    }

    public GetEmailRequest toGetEmailRequest(JwtToken jwtToken) {
        return GetEmailRequest.builder()
            .userKey(parsingJwt(jwtToken.getJwtToken(), userKey))
            .build();
    }


    public GetEmailResponse toGetEmailResponse(String email) {
        return GetEmailResponse.builder()
            .email(email)
            .build();
    }


        private String parsingJwt(String jwtToken, String key) {
            return JWT.require(Algorithm.HMAC256(JwtProperties.SECRET)).build().verify(jwtToken).getClaim(key).asString();
    }


}
