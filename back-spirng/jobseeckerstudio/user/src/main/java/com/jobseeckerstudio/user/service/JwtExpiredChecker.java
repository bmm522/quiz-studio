package com.jobseeckerstudio.user.service;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.exception.NotFoundSaltException;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;

import com.jobseeckerstudio.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtExpiredChecker {

    private final UserRepository userRepository;

    public String check(JwtToken jwtToken) {

        User user = userRepository.findBySalt(jwtToken.getRefreshToken())
            .orElseThrow(() -> new NotFoundSaltException("refreshToken에 해당되는 유저 정보가 없습니다."));
        if(!jwtToken.checkExpiredToken()){
            return JwtMaker.makeAccessToken(user);
        }


        return jwtToken.getJwtToken();
    }
}
