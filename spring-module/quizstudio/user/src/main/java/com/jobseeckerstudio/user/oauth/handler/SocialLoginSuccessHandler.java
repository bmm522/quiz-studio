package com.jobseeckerstudio.user.oauth.handler;

import com.jobseeckerstudio.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.oauth.cookie.CookieMaker;
import com.jobseeckerstudio.user.oauth.cookie.TokenCookie;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class SocialLoginSuccessHandler implements AuthenticationSuccessHandler {

	private final UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		log.info("login processing ");
		log.info(request.getRequestURI());

		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		User user = principalDetails.getUser();
		JwtToken jwtToken = JwtMaker.create(user);
		settingUserAndGetRefreshToken(user, jwtToken);
		TokenCookie tokenCookie = CookieMaker.INSTANCE.toCookie(jwtToken);
		addCookie(response, tokenCookie);
		response.addHeader("Set-Cookie", tokenCookie.getAuthorizationCookie().toString());
		response.addHeader("Set-Cookie", tokenCookie.getRefreshTokenCookie().toString());
		response.sendRedirect("https://www.quizstudio.site/main");
	}


	private void addCookie(HttpServletResponse response, TokenCookie tokenCookie) {
		response.addCookie(tokenCookie.getAuthorizationCookie());
		response.addCookie(tokenCookie.getRefreshTokenCookie());
	}

	private void settingUserAndGetRefreshToken(User user, JwtToken jwtToken) {
		Optional<User> userOptional = userRepository.findByUserKey(user.getUserKey());
		if (userOptional.isEmpty()) {
			setUserSaltAndSave(user, jwtToken.getRefreshToken());
		} else {
			updateUserSaltAndRefreshTokenIfNotExpired(userOptional.get(), jwtToken);
		}
	}

	/**
	 * 사용자의 솔트와 리프레시 토큰을 설정하고 저장합니다.
	 *
	 * @param user         사용자 정보
	 * @param refreshToken 리프레시 토큰
	 */
	public void setUserSaltAndSave(User user, String refreshToken) {
		user.setEmailWithEncryption();
		user.setSalt(refreshToken);
		userRepository.save(user);
	}

	/**
	 * 저장된 사용자의 솔트와 리프레시 토큰을 업데이트합니다. 리프레시 토큰이 만료되지 않은 경우에만 업데이트합니다.
	 *
	 * @param savedUser 저장된 사용자 정보
	 * @param jwtToken  JWT 토큰
	 */
	public void updateUserSaltAndRefreshTokenIfNotExpired(User savedUser, JwtToken jwtToken) {
		String refreshTokenFromSavedUser = savedUser.getSalt();
		jwtToken.setRefreshToken(refreshTokenFromSavedUser);
		if (!jwtToken.checkExpiredRefreshToken()) {
			String newRefreshToken = JwtMaker.makeRefreshToken();
			savedUser.setSalt(newRefreshToken);
			jwtToken.setRefreshToken(newRefreshToken);
			userRepository.save(savedUser);
		}
	}


}
