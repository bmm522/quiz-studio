package com.jobseeckerstudio.bmm522.user.oauth.info;

import com.jobseeckerstudio.bmm522.user.entity.User;
import com.jobseeckerstudio.bmm522.user.entity.Status;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class KakaoUserInfo implements SocialUserInfo{

    private Map<String, Object> userInfoMap;

    public KakaoUserInfo(Map<String, Object> userInfoMap){
        this.userInfoMap = userInfoMap;
    }

    @Override
    public String getUserKey() {
        return String.valueOf(userInfoMap.get("id"));
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

        return User.builder()
                .userKey(Provider.Kakao.getProvider()+"_"+String.valueOf(userInfoMap.get("id")))
                .password("null")
                .email((String)((Map)userInfoMap.get("kakao_account")).get("email"))
                .status(Status.Y)
                .build();
    }
}
