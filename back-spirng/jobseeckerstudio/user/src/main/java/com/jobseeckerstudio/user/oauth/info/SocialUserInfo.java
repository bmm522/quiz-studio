package com.jobseeckerstudio.user.oauth.info;

import com.jobseeckerstudio.user.domain.user.User;

public interface SocialUserInfo {

    String getUserKey();
    Provider getProvider();
    String getEmail();
    User toUserEntity();
}
