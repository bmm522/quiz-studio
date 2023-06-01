package com.jobseeckerstudio.user.jwt;

import com.jobseeckerstudio.user.exception.InvalidTokenException;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Base64;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtToken {

	private String jwtToken;
	private String refreshToken;

	@Builder
	public JwtToken(String jwtToken, String refreshToken) {
		this.jwtToken = jwtToken;
		this.refreshToken = refreshToken;
	}


	public void checkValidateJwtToken() {
		if (jwtToken == null || jwtToken.isEmpty() || !jwtToken.startsWith(
			JwtProperties.TOKEN_PREFIX)) {
			throw new InvalidTokenException("잘못된 토큰 정보입니다.");
		}
	}

	public void checkValidateRefreshToken() {
		if (refreshToken == null || refreshToken.isEmpty() || !refreshToken.startsWith(
			JwtProperties.REFRESH_PREFIX)) {
			throw new InvalidTokenException("잘못된 토큰 정보입니다.");
		}
	}

	public boolean checkExpiredToken() {
		try {
			Jws<Claims> claims = Jwts.parser()
				.setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
				.parseClaimsJws(jwtToken.replace(JwtProperties.TOKEN_PREFIX, ""));

			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
		} catch (JwtException e) {
			return false;
		}
		return true;
	}

	public boolean checkExpiredRefreshToken() {
//		System.out.println(JwtProperties.REFRESH_PREFIX);
//		System.out.println(refreshToken);
		try {
			Jws<Claims> claims = Jwts.parser()
				.setSigningKey(Base64.getEncoder().encodeToString(JwtProperties.SECRET.getBytes()))
				.parseClaimsJws(refreshToken.replace(JwtProperties.REFRESH_PREFIX, ""));

			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
		} catch (JwtException e) {
			return false;
		}

		return true;
	}

}

