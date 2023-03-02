package com.jobseecker.jobseeckerstudio.login.oauth.info;

import com.jobseecker.jobseeckerstudio.login.repository.User;

public interface SocialUserInfo {

    String getUserKey();
    Provider getProvider();
    String getEmail();
    User toUserEntity();
}
