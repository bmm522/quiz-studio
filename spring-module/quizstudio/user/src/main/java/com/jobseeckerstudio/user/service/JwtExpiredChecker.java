package com.jobseeckerstudio.user.service;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.exception.NotFoundSaltException;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtExpiredChecker {

	private final UserRepository userRepository;

	public JwtToken check(JwtToken jwtToken) {
		System.out.println(jwtToken.getRefreshToken());

		User user = userRepository.findBySalt(jwtToken.getRefreshToken())
			.orElseThrow(() -> new NotFoundSaltException("refreshToken에 해당되는 유저 정보가 없습니다."));

		if (!jwtToken.checkExpiredRefreshToken()) {
			String newRefreshToken = JwtMaker.makeRefreshToken();
			user.setSalt(newRefreshToken);
			jwtToken.setRefreshToken(newRefreshToken);
			userRepository.save(user);
		}

		if (!jwtToken.checkExpiredToken()) {
			String newAccessToken = JwtMaker.makeAccessToken(user);
			jwtToken.setJwtToken(newAccessToken);
			return jwtToken;
		}

		return jwtToken;
	}
}
