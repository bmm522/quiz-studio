package com.jobseeckerstudio.bmm522.user.service.user;

import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;

import java.util.Optional;

public interface ReadUserService {

    public Optional<User> findByUserKeyWhenSocialLogin(SocialUserInfo socialUserInfo);
}
