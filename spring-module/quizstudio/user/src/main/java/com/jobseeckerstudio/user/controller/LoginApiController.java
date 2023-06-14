package com.jobseeckerstudio.user.controller;

import com.jobseeckerstudio.user.controller.dto.CommonResponse;
import com.jobseeckerstudio.user.controller.dto.ResponseHandler;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.mapper.JwtMapper;
import com.jobseeckerstudio.user.oauth.cookie.CookieMaker;
import com.jobseeckerstudio.user.oauth.cookie.TokenCookie;
import com.jobseeckerstudio.user.service.JwtExpiredChecker;
import com.jobseeckerstudio.user.service.ReadUserService;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("user/api/v1")
@Controller
@RequiredArgsConstructor
public class LoginApiController {

	private final ReadUserService readUserService;

	private final JwtExpiredChecker jwtExpiredChecker;


	/**
	 * 소셜 로그인 페이지로 이동하는 메서드입니다.
	 *
	 * @param social 소셜 미디어 타입
	 * @return 소셜 로그인 페이지로의 리다이렉트 URL
	 */
	@GetMapping("social/login/{social}")
	public String moveSocialLoginForm(@PathVariable("social") String social) {
		return "redirect:/oauth2/authorization/" + social;
	}

	/**
	 * JWT 토큰을 이용하여 이메일을 가져오는 메서드입니다.
	 *
	 * @param request HTTP 요청 객체
	 * @return 이메일 정보와 함께 CommonResponse 객체
	 */
	@GetMapping("/email")
	public @ResponseBody CommonResponse<?> getEmail(HttpServletRequest request) {
		final JwtToken jwtToken = JwtMapper.toJwtToken(request);
		final GetEmailResponse dto = readUserService.getEmail(jwtToken);
		return ResponseHandler.handle(200, "이메일 불러오기 성공", dto);
	}

	/**
	 * 만료된 JWT 토큰을 체크하는 메서드입니다.
	 *
	 * @param request HTTP 요청 객체
	 * @return 체크된 JWT 토큰과 함께 CommonResponse 객체
	 */
	@GetMapping("/check-expired-jwt")
	public @ResponseBody CommonResponse<?> checkExpiredJwt(HttpServletRequest request) {
		final JwtToken jwtToken = JwtMapper.toJwtToken(request);
		final JwtToken checkedToken = jwtExpiredChecker.check(jwtToken);

		return ResponseHandler.handle(200, "jwt 체크완료", checkedToken);
	}

	/**
	 * 만료된 JWT 토큰을 체크하는 메서드입니다.
	 *
	 * @param request HTTP 요청 객체
	 * @return 체크된 JWT 토큰과 함께 CommonResponse 객체
	 */
	@GetMapping("/logout")
	public void logout(HttpServletRequest request,
		HttpServletResponse response) {
		TokenCookie tokenCookie = CookieMaker.INSTANCE.toCookieWhenLogout();
		response.addCookie(tokenCookie.getAuthorizationCookie());
		response.addCookie(tokenCookie.getRefreshTokenCookie());
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
	}


}
