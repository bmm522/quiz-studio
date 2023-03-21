package com.jobseecker.jobseeckerstudio.login.oauth.info;

import com.jobseecker.jobseeckerstudio.login.repository.User;
import com.jobseecker.jobseeckerstudio.login.repository.entity.Status;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class GoogleUserInfo implements SocialUserInfo{

    private ConcurrentMap<String, Object> userInfoMap;

    public GoogleUserInfo(ConcurrentMap<String, Object> userInfoMap){
        this.userInfoMap = userInfoMap;

    }

    @Override
    public String getUserKey() {
        return (String)userInfoMap.get("sub");
    }

    @Override
    public Provider getProvider() {
        return Provider.Google;
    }

    @Override
    public String getEmail() {
        return (String)userInfoMap.get("email");
    }

    @Override
    public User toUserEntity() {
        return User.builder()
                .userKey(Provider.Google.getProvider()+"_"+userInfoMap.get("sub"))
                .password("null")
                .email((String) userInfoMap.get("email"))
                .status(Status.Y)
                .build();
    }
}
