package com.jobseeckerstudio.user.jwt.filter;

import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.mapper.JwtMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final String googleUrl = "/user/api/v1/social/login/google";
	private final String kakaoUrl = "/user/api/v1/social/login/kakao";

	private final String newToken = "/user/api/v1/check-expired-jwt";

	private final String GUEST_LOGIN = "guest";

	public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		try {
			if (shouldSkipFilterWhenSocilaLogin(request)) {
				chain.doFilter(request, response);
				return;
			}

			if (shouldSkipFilterWhenGuestLogin(request)) {
				chain.doFilter(request, response);
				return;
			}
			JwtToken jwtToken = JwtMapper.toJwtToken(request);
			jwtToken.checkValidateJwtToken();
			jwtToken.checkValidateRefreshToken();
			chain.doFilter(request, response);
		} catch (Exception e) {
			handleException(response, e, 401);
		}
	}

	/**
	 * guest 로그인을 확인하는 메서드입니다.
	 *
	 * @param request HTTP 요청
	 * @return 필터를 건너뛸지 여부 (true: 건너뛰기, false: 필터 실행)
	 */
	private boolean shouldSkipFilterWhenGuestLogin(HttpServletRequest request) {
		String header = request.getHeader("authorization");
		return GUEST_LOGIN.equals(header);
	}

	private void handleException(HttpServletResponse response, Exception e, Integer status)
		throws IOException {
		response.setStatus(status);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
		String errorName = e.getClass().getSimpleName();
		String message = e.getMessage();
		response.getWriter().write(
			String.format("{\"status\": %d, \"errorName\": \"%s\", \"msg\": \"%s\"}", status,
				errorName, message));
		response.getWriter().flush();
		response.getWriter().close();
	}

	/**
	 * (소셜로그인의 경우)필터를 건너뛸지 여부를 확인하는 메서드입니다.
	 *
	 * @param request HTTP 요청
	 * @return 필터를 건너뛸지 여부 (true: 건너뛰기, false: 필터 실행)
	 */
	public boolean shouldSkipFilterWhenSocilaLogin(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		log.info("filter : " + requestURI);
		return googleUrl.equals(requestURI) || kakaoUrl.equals(requestURI) || newToken.equals(
			requestURI) || "/favicon.ico".equals(requestURI);
	}


}
