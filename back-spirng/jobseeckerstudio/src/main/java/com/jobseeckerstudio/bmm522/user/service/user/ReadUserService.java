package com.jobseeckerstudio.bmm522.user.service.user;

import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailResponse;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ReadUserService {

    public Optional<User> findByUserKeyWhenSocialLogin(SocialUserInfo socialUserInfo);

    public Optional<User> findByUserKey(String username);

    public GetEmailResponse getEmail(JwtToken jwtToken);
}
