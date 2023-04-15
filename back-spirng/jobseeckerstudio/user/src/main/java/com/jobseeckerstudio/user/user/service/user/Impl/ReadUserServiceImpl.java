package com.jobseeckerstudio.user.user.service.user.Impl;

import com.jobseeckerstudio.user.global.exception.NotFoundUserException;
import com.jobseeckerstudio.user.user.encryption.Decryptor;
import com.jobseeckerstudio.user.user.entity.user.User;
import com.jobseeckerstudio.user.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.user.repository.user.UserQueryRepository;
import com.jobseeckerstudio.user.user.service.user.ReadUserService;
import com.jobseeckerstudio.user.user.service.user.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.user.user.service.user.dto.GetEmailRequest;
import com.jobseeckerstudio.user.user.service.user.dto.GetEmailResponse;
import com.jobseeckerstudio.user.user.service.user.mapper.UserServiceMapper;
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

    @Override
    public Optional<User> findByUserKey(String userKey) {
        return  userQueryRepository.findByUserKey(userKey);
    }

    @Override
    public GetEmailResponse getEmail(JwtToken jwtToken) {
        GetEmailRequest dto = mapper.toGetEmailRequest(jwtToken);
        User user = getUser(dto);
        String email = Decryptor.decrypt(user.getEmail());
        return mapper.toGetEmailResponse(email);
    }

    private User getUser(GetEmailRequest dto) {
        return userQueryRepository.findByUserKey(dto.getUserKey())
            .orElseThrow(() -> new NotFoundUserException("userKey에 해당하는 유저 정보가 없습니다."));
    }
}
