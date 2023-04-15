package com.jobseeckerstudio.user.user.service.user;

import com.jobseeckerstudio.user.user.entity.user.User;
import com.jobseeckerstudio.user.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.user.service.user.dto.GetEmailResponse;

import java.util.Optional;

public interface ReadUserService {

    public Optional<User> findByUserKeyWhenSocialLogin(SocialUserInfo socialUserInfo);

    public Optional<User> findByUserKey(String username);

    public GetEmailResponse getEmail(JwtToken jwtToken);
}
