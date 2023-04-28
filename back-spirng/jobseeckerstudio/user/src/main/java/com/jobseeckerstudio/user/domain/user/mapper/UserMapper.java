package com.jobseeckerstudio.user.domain.user.mapper;

import com.jobseeckerstudio.user.domain.Status;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.oauth.info.Provider;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;

import javax.servlet.http.HttpServletRequest;

public class UserMapper {

    public static User toUserFromRequest(HttpServletRequest request) {
        return User.builder()
                .userKey((String) request.getAttribute("userKey"))
                .password("null")
                .build();
    }

    public static User toUserFromSocialInfo(SocialUserInfo socialUserInfo) {
        return User.builder()
            .userKey(Provider.Google.getProvider()+"_"+socialUserInfo.getUserKey())
            .password("null")
            .email((String) socialUserInfo.getEmail())
            .status(Status.Y)
            .build();
    }
}
