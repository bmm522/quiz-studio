package com.jobseeckerstudio.user.user.oauth.info;

import com.jobseeckerstudio.user.user.entity.user.User;

public interface SocialUserInfo {

    String getUserKey();
    Provider getProvider();
    String getEmail();
    User toUserEntity();
}
