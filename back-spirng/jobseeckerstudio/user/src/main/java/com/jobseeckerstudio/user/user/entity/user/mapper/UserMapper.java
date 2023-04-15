package com.jobseeckerstudio.user.user.entity.user.mapper;

import com.jobseeckerstudio.user.user.entity.user.Status;
import com.jobseeckerstudio.user.user.entity.user.User;
import com.jobseeckerstudio.user.user.oauth.info.Provider;
import com.jobseeckerstudio.user.user.oauth.info.SocialUserInfo;

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
