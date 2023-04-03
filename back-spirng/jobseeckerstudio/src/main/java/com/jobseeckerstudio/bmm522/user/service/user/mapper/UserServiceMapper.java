package com.jobseeckerstudio.bmm522.user.service.user.mapper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.service.user.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailRequest;
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


        private String parsingJwt(String jwtToken, String key) {
            return JWT.require(Algorithm.HMAC256(JwtProperties.SECRET)).build().verify(jwtToken).getClaim(key).asString();
    }

}
