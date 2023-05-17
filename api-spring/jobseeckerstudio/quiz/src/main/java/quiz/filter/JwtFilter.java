package quiz.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import quiz.exception.ExpiredTokenException;
import quiz.exception.InvalidTokenException;
import quiz.exception.NullUserKeyFromJwtTokenException;
import quiz.properties.JwtProperties;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal (HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String jwtToken = extractJwtToken(request);
		Jws<Claims> claims = parseJwtToken(jwtToken);
		String userKey = extractUserKey(claims);
		request.setAttribute("userKey", userKey);
		filterChain.doFilter(request, response);
	}

	private String extractJwtToken (HttpServletRequest request) {
		final String authorizationHeader = request.getHeader(JwtProperties.HEADER_JWT);

		if (authorizationHeader == null || !authorizationHeader.startsWith(JwtProperties.TOKEN_PREFIX)) {
			throw new InvalidTokenException("잘못된 토큰 정보입니다.");
		}
		return authorizationHeader.replace(JwtProperties.TOKEN_PREFIX, "");
	}

	private Jws<Claims> parseJwtToken (String jwtToken) {
		try {
			return Jwts.parser()
				.setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
				.parseClaimsJws(jwtToken);
		} catch (ExpiredJwtException e) {
			throw new ExpiredTokenException("유효시간이 지난 토큰입니다.");
		}
	}

	private String extractUserKey (Jws<Claims> claims) {
		String userKey = claims.getBody().get("userKey", String.class);
		if (userKey == null) {
			throw new NullUserKeyFromJwtTokenException("jwt토큰으로부터 userKey를 찾을 수 없습니다.");
		}
		return userKey;
	}

}
