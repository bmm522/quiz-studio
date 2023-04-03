package com.jobseeckerstudio.bmm522.user.service.user.Impl;

import com.jobseeckerstudio.bmm522.global.exception.NotFoundSaltException;
import com.jobseeckerstudio.bmm522.global.exception.NotFoundUserException;
import com.jobseeckerstudio.bmm522.user.encryption.Encryptor;
import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.repository.user.UserQueryRepository;
import com.jobseeckerstudio.bmm522.user.service.user.ReadUserService;
import com.jobseeckerstudio.bmm522.user.service.user.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailRequest;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailResponse;
import com.jobseeckerstudio.bmm522.user.service.user.mapper.UserServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadUserServiceImpl implements ReadUserService {

    private final UserServiceMapper mapper;
    private final UserQueryRepository userQueryRepository;

    private final Encryptor encryptor;


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
        String email = encryptor.decrypt(user.getEmail());
        return mapper.toGetEmailResponse(email);
    }

    private User getUser(GetEmailRequest dto) {
        return userQueryRepository.findByUserKey(dto.getUserKey())
            .orElseThrow(() -> new NotFoundUserException("userKey에 해당하는 유저 정보가 없습니다."));
    }
}
