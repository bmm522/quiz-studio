package com.jobseeckerstudio.bmm522.user.service.user.Impl;

import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.repository.UserQueryRepository;
import com.jobseeckerstudio.bmm522.user.service.user.ReadUserService;
import com.jobseeckerstudio.bmm522.user.service.user.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.bmm522.user.service.user.mapper.UserServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadUserServiceImpl implements ReadUserService {

    private final UserServiceMapper mapper;
    private final UserQueryRepository userQueryRepository;
    @Override
    public Optional<User> findByUserKeyWhenSocialLogin(SocialUserInfo socialUserInfo) {
        FindUserWhenSocialLoginRequest dto = mapper.toFindUserWhenSocialLoginRequest(socialUserInfo);
        return userQueryRepository.findByUserKey(dto.getUserKey());
    }
}
