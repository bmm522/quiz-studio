package com.jobseeckerstudio.user.service;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.exception.NotFoundSaltException;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtExpiredChecker {

	private final UserRepository userRepository;

	/**
	 * JWT 토큰의 만료 여부를 확인하고 만료되지 않은 경우 유효한 토큰을 반환합니다. 만료된 경우 refreshToken을 갱신하고 accessToken을 재발급하여
	 * 토큰을 업데이트합니다.
	 *
	 * @param jwtToken JWT 토큰
	 * @return 만료되지 않은 유효한 JWT 토큰
	 * @throws NotFoundSaltException refreshToken에 해당되는 유저 정보가 없는 경우 발생하는 예외
	 */
	public JwtToken check(JwtToken jwtToken) {
		User user = userRepository.findBySalt(jwtToken.getRefreshToken())
			.orElseThrow(() -> new NotFoundSaltException("refreshToken에 해당되는 유저 정보가 없습니다."));

		if (!jwtToken.isRefreshTokenValid()) {
			String newRefreshToken = JwtMaker.makeRefreshToken();
			user.setSalt(newRefreshToken);
			jwtToken.setRefreshToken(newRefreshToken);
			userRepository.save(user);
		}

		if (!jwtToken.isAccessTokenValid()) {
			String newAccessToken = JwtMaker.makeAccessToken(user);
			jwtToken.setJwtToken(newAccessToken);
			return jwtToken;
		}

		return jwtToken;
	}
}
