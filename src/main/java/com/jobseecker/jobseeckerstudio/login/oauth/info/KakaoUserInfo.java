package com.jobseecker.jobseeckerstudio.login.oauth.info;

import com.jobseecker.jobseeckerstudio.login.repository.User;
import com.jobseecker.jobseeckerstudio.login.repository.entity.Status;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;

public class KakaoUserInfo implements SocialUserInfo{

    private ConcurrentMap<String, Object> userInfoMap;

    public KakaoUserInfo(ConcurrentMap<String, Object> userInfoMap){
        this.userInfoMap = userInfoMap;
    }

    @Override
    public String getUserKey() {
        return (String) userInfoMap.get("id");
    }

    @Override
    public Provider getProvider() {
        return Provider.Kakao;
    }

    @Override
    public String getEmail() {
        return (String)((Map)userInfoMap.get("kakao_account")).get("email");
    }

    @Override
    public User toUserEntity() {
        String email = (String)userInfoMap.get("id");
        return User.builder()
                .userKey(Provider.Kakao.getProvider()+"_"+email)
                .password("null")
                .email(email)
                .status(Status.Y)
                .build();
    }
}
