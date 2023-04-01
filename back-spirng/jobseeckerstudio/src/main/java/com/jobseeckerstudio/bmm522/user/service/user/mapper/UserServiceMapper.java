package com.jobseeckerstudio.bmm522.user.service.user.mapper;

import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.service.user.dto.FindUserWhenSocialLoginRequest;
import org.springframework.stereotype.Component;

@Component
public class UserServiceMapper {

    public FindUserWhenSocialLoginRequest toFindUserWhenSocialLoginRequest(SocialUserInfo socialUserInfo) {
        return FindUserWhenSocialLoginRequest.builder()
            .userKey(socialUserInfo.getProvider()+"_"+ socialUserInfo.getUserKey())
            .build();
    }
}
