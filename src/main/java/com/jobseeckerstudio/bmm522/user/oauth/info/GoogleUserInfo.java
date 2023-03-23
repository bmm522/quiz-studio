package com.jobseeckerstudio.bmm522.user.oauth.info;

import com.jobseeckerstudio.bmm522.user.entity.User;
import com.jobseeckerstudio.bmm522.user.entity.Status;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class GoogleUserInfo implements SocialUserInfo{

    private Map<String, Object> userInfoMap;

    public GoogleUserInfo(Map<String, Object> userInfoMap){
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
