package com.jobseeckerstudio.bmm522.user.service.user.mapper;

import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.service.user.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailRequest;
import org.springframework.stereotype.Component;

@Component
public class UserServiceMapper {

    public FindUserWhenSocialLoginRequest toFindUserWhenSocialLoginRequest(SocialUserInfo socialUserInfo) {
        return FindUserWhenSocialLoginRequest.builder()
            .userKey(socialUserInfo.getProvider()+"_"+ socialUserInfo.getUserKey())
            .build();
    }

    public GetEmailRequest toGetEmailRequest(String authorization, String refreshToken) {
        return GetEmailRequest.builder()
            .authorization(authorization)
            .refreshToken(refreshToken)
            .build();
    }
}
