package com.jobseeckerstudio.user.user.service.user;

import com.jobseeckerstudio.user.user.entity.user.User;
import com.jobseeckerstudio.user.user.oauth.info.SocialUserInfo;

public interface CreateUserService {
    User saveWhenSocialLogin(SocialUserInfo socialUserInfo);
}
