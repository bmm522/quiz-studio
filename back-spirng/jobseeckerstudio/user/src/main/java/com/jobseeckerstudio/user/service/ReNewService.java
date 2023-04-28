package com.jobseeckerstudio.user.service;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.exception.NotFoundSaltException;
import com.jobseeckerstudio.user.jwt.JwtTokenFactory;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReNewService {
    private final UserRepository userRepository;
    public JwtToken reNewToken(JwtToken jwtToken) {
        User user = userRepository.findBySalt(jwtToken.getRefreshToken())
            .orElseThrow(() ->new NotFoundSaltException("해당 refreshToken값의 유저 정보를 찾을 수 없습니다."));

        JwtToken newToken = JwtTokenFactory.create(user);

        if(!jwtToken.checkValidateRefreshToken()) {
            user.setSalt(newToken.getRefreshToken());
        }

        return newToken;
    }
}
