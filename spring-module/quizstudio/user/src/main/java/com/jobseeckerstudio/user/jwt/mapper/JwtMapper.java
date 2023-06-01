package com.jobseeckerstudio.user.jwt.mapper;

import com.jobseeckerstudio.user.exception.NotFoundTokenFromHeaderException;
import com.jobseeckerstudio.user.jwt.JwtToken;
import javax.servlet.http.HttpServletRequest;

public class JwtMapper {

	// public static Optional<JwtToken> toJwtTokenOptional(HttpServletRequest request) {
	//     return Optional.ofNullable(JwtToken.builder()
	//         .jwtToken(request.getHeader("authorization"))
	//         .refreshToken(request.getHeader("refreshToken"))
	//         .build());
	// }

	/**
	 * HttpServletRequest에서 JWT 토큰을 가져와 JwtToken 객체로 변환합니다.
	 *
	 * @param request HTTP 요청
	 * @return JwtToken 객체
	 * @throws NotFoundTokenFromHeaderException JWT 토큰이 헤더에서 찾을 수 없는 경우 발생하는 예외
	 */
	public static JwtToken toJwtToken(HttpServletRequest request) {
		String jwtToken = getHeaderValue(request, "authorization");
		String refreshToken = getHeaderValue(request, "refreshToken");

		return JwtToken.builder()
			.jwtToken(jwtToken)
			.refreshToken(refreshToken)
			.build();
	}

	private static String getHeaderValue(HttpServletRequest request, String headerName) {
		String headerValue = request.getHeader(headerName);
		if (headerValue == null || headerValue.isEmpty()) {
			throw new NotFoundTokenFromHeaderException(
				String.format("헤더에서 %s 정보를 찾을 수 없습니다.", headerName));
		}
		return headerValue;
	}
}
