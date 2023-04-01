package com.jobseeckerstudio.bmm522.user.service.user;

import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;

public interface CreateUserService {
    User saveWhenSocialLogin(SocialUserInfo socialUserInfo);
}
