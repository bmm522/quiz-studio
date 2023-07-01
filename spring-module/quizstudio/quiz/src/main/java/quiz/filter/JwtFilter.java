package quiz.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import quiz.global.exception.ExpiredTokenException;
import quiz.global.exception.InvalidTokenException;
import quiz.global.exception.NullUserKeyFromJwtTokenException;
import quiz.properties.JwtProperties;


@Component
public class JwtFilter extends OncePerRequestFilter {


	private static String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookieName.equals(cookies[i].getName())) {
				return cookies[i].getValue();
			}
		}
		return String.format("쿠키에서 %s 정보를 찾을 수 없습니다.", cookieName);
	}

	/**
	 * 요청을 필터링하여 JWT 토큰을 추출하고 해석하여 사용자 키를 추출한 후, 요청에 userKey 속성을 설정하는 메서드입니다.
	 *
	 * @param request     HTTP 요청
	 * @param response    HTTP 응답
	 * @param filterChain 필터 체인
	 * @throws ServletException 필터 체인 내에서 예외가 발생한 경우
	 * @throws IOException      I/O 예외가 발생한 경우
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		if (guestLogin(request)) {
			filterChain.doFilter(request, response);
			return;
		}
		String jwtToken = extractJwtToken(request);
		Jws<Claims> claims = parseJwtToken(jwtToken);
		String userKey = extractUserKey(claims);
		request.setAttribute("userKey", userKey);

		filterChain.doFilter(request, response);
	}

	/**
	 * guest 로그인을 확인하는 메서드입니다.
	 *
	 * @param request HTTP 요청
	 * @return 필터를 건너뛸지 여부 (true: 건너뛰기, false: 필터 실행)
	 */
	private boolean guestLogin(HttpServletRequest request) {
		String header = request.getHeader("authorization");
		String GUEST_LOGIN = "guest";
		return GUEST_LOGIN.equals(header);
	}

	/**
	 * HTTP 요청에서 JWT 토큰을 추출하는 메서드입니다.
	 *
	 * @param request HTTP 요청
	 * @return 추출된 JWT 토큰
	 * @throws InvalidTokenException 토큰 정보가 잘못된 경우 예외 발생
	 */
	private String extractJwtToken(HttpServletRequest request) {
//		final String authorizationHeader = getCookieValue(request, "Authorization").replace("+",
//			" ");
		final String authorizationHeader = request.getHeader(JwtProperties.HEADER_JWT);
		if (authorizationHeader == null || !authorizationHeader.startsWith(
			JwtProperties.TOKEN_PREFIX)) {
			throw new InvalidTokenException("잘못된 토큰 정보입니다.");
		}
		return authorizationHeader.replace(JwtProperties.TOKEN_PREFIX, "");
	}

	/**
	 * JWT 토큰을 해석하여 클레임을 추출하는 메서드입니다.
	 *
	 * @param jwtToken JWT 토큰
	 * @return 추출된 클레임
	 * @throws ExpiredTokenException 토큰의 유효 기간이 지난 경우 예외 발생
	 */
	private Jws<Claims> parseJwtToken(String jwtToken) {
		try {
			return Jwts.parser()
				.setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
				.parseClaimsJws(jwtToken);
		} catch (ExpiredJwtException e) {
			throw new ExpiredTokenException("유효시간이 지난 토큰입니다.");
		}
	}

	/**
	 * 클레임에서 사용자 키를 추출하는 메서드입니다.
	 *
	 * @param claims 클레임
	 * @return 추출된 사용자 키
	 * @throws NullUserKeyFromJwtTokenException JWT 토큰에서 사용자 키를 찾을 수 없는 경우 예외 발생
	 */
	private String extractUserKey(Jws<Claims> claims) {
		String userKey = claims.getBody().get("userKey", String.class);
		if (userKey == null) {
			throw new NullUserKeyFromJwtTokenException("jwt토큰으로부터 userKey를 찾을 수 없습니다.");
		}
		return userKey;
	}
}
