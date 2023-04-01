package com.jobseeckerstudio.bmm522.user.oauth.info;

import com.jobseeckerstudio.bmm522.user.entity.user.User;

public interface SocialUserInfo {

    String getUserKey();
    Provider getProvider();
    String getEmail();
    User toUserEntity();
}
