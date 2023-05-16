package com.jobseeckerstudio.user.service;

import com.jobseeckerstudio.user.exception.NotFoundUserException;
import com.jobseeckerstudio.user.encrypt.Decryptor;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
import com.jobseeckerstudio.user.service.mapper.UserServiceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReadUserService  {

    private final UserRepository userRepository;


//    public Optional<User> findByUserKeyWhenSocialLogin(SocialUserInfo socialUserInfo) {
//        FindUserWhenSocialLoginRequest dto = UserServiceMapper.toFindUserWhenSocialLoginRequest(socialUserInfo);
//        return userRepository.findByUserKey(dto.getUserKey());
//    }


    public Optional<User> findByUserKey(String userKey) {
        return  userRepository.findByUserKey(userKey);
    }


    @Transactional(rollbackFor = RuntimeException.class)
    public GetEmailResponse getEmail(JwtToken jwtToken) {
        GetEmailRequest dto = UserServiceMapper.toGetEmailRequest(jwtToken);
        User user = getUser(dto);
        String email = Decryptor.decrypt(user.getEmail());
        return UserServiceMapper.toGetEmailResponse(email);
    }

    public User getUser(GetEmailRequest dto) {
        return userRepository.findByUserKey(dto.getUserKey())
            .orElseThrow(() -> new NotFoundUserException("userKey에 해당하는 유저 정보가 없습니다."));
    }
}