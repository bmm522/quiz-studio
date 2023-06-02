package com.jobseeckerstudio.user.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.encrypt.Encryptor;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import java.util.Date;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-dev.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("jwt토큰 유효시간 체크 e2e 테스트")
public class CheckJwtExpiredE2ETest {

	private static final int EXPIRATION_TIME = 1000 * 60 * 60;

	private static final int REFRESHTOKEN_EXPIRATION_TIME = 14 * 24 * 6 * 10 * 60000;
	@Autowired
	private TestRestTemplate rt;

	@Autowired
	private UserRepository userRepository;


	@BeforeEach
	public void readyData() {

	}

	@AfterAll
	public void deleteAll() {
		userRepository.deleteByUserKey("test_user");
		userRepository.deleteByUserKey("test2");
		userRepository.deleteByUserKey("test3");
	}

	private HttpHeaders createHeaders(String jwtToken, String refreshToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(JwtProperties.HEADER_JWT, jwtToken);
		headers.set(JwtProperties.HEADER_REFRESH, refreshToken);
		return headers;
	}

	private ResponseEntity<String> getResponse(HttpHeaders headers) {
		return rt.exchange("/api/v1/check-expired-jwt", HttpMethod.GET, new HttpEntity<>(headers),
			String.class);

	}

	@Test
	@DisplayName("jwt 토큰 유효시간 체크 정상적인  요청")
	public void checkJwtExpiredWhenSuccess() {
		User testUser = User.builder()
			.userKey("test_user")
			.email(Encryptor.encrypt("test_email@test.com"))
			.build();
		JwtToken jwtToken = JwtToken.builder()
			.jwtToken(JwtProperties.TOKEN_PREFIX + JWT.create()
				.withSubject(testUser.getUserKey())
				.withIssuer(JwtProperties.ISS)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.withClaim("userKey", testUser.getUserKey())
				.sign(Algorithm.HMAC256(JwtProperties.SECRET)))
			.refreshToken(JwtProperties.REFRESH_PREFIX + JWT.create()
				.withSubject(testUser.getUserKey())
				.withIssuer(JwtProperties.ISS)
				.withExpiresAt(new Date(System.currentTimeMillis() + REFRESHTOKEN_EXPIRATION_TIME))
				.sign(Algorithm.HMAC256(JwtProperties.SECRET)))
			.build();
		testUser.setSalt(jwtToken.getRefreshToken());
		userRepository.save(testUser);
		HttpHeaders headers = createHeaders(jwtToken.getJwtToken(), jwtToken.getRefreshToken());
		ResponseEntity<String> response = getResponse(headers);
		DocumentContext dc = JsonPath.parse(response.getBody());
		System.out.println(response.getBody());
		Integer status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String responseToken = dc.read("$.data.jwtToken");

		assertThat(status).isEqualTo(200);
		assertThat(msg).isEqualTo("jwt 체크완료");
		assertThat(jwtToken.getJwtToken()).isEqualTo(responseToken);

	}

	@Test
	@DisplayName("jwt 토큰 유효시간 체크 accessToken 유효시간 지났을 경우")
	public void checkJwtExpiredWhenExpiredAccessToken() {
		User user = User.builder().userKey("test2").email("test@test.com").build();
		JwtToken expiredAccessToken = JwtToken.builder()
			.jwtToken(JwtProperties.TOKEN_PREFIX + JWT.create()
				.withSubject(user.getUserKey())
				.withIssuer(JwtProperties.ISS)
				.withExpiresAt(new Date(System.currentTimeMillis() - 100000000))
				.withClaim("userKey", user.getUserKey())
				.sign(Algorithm.HMAC256(JwtProperties.SECRET)))
			.refreshToken(JwtProperties.REFRESH_PREFIX + JWT.create()
				.withSubject(user.getUserKey())
				.withIssuer(JwtProperties.ISS)
				.withExpiresAt(new Date(System.currentTimeMillis() + REFRESHTOKEN_EXPIRATION_TIME))
				.sign(Algorithm.HMAC256(JwtProperties.SECRET)))
			.build();

		user.setSalt(expiredAccessToken.getRefreshToken());
		userRepository.save(user);

		HttpHeaders headers = createHeaders(expiredAccessToken.getJwtToken(),
			expiredAccessToken.getRefreshToken());
		ResponseEntity<String> response = getResponse(headers);
		DocumentContext dc = JsonPath.parse(response.getBody());
		System.out.println(response.getBody());
		Integer status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String responseToken = dc.read("$.data.jwtToken");
		assertThat(status).isEqualTo(200);
		assertThat(msg).isEqualTo("jwt 체크완료");
		assertThat(expiredAccessToken.getJwtToken()).isNotEqualTo(responseToken);

	}

	@Test
	@DisplayName("jwt 토큰 유효시간 체크 refreshToken 유효시간 지났을 경우")
	public void checkJwtExpiredWhenExpiredRefreshToken() {
		User user = User.builder().userKey("test3").email("test@test.com").build();
		JwtToken expiredRefreshToken = JwtToken.builder()
			.jwtToken(JwtProperties.TOKEN_PREFIX + JWT.create()
				.withSubject(user.getUserKey())
				.withIssuer(JwtProperties.ISS)
				.withExpiresAt(new Date(System.currentTimeMillis() - EXPIRATION_TIME))
				.withClaim("userKey", user.getUserKey())
				.sign(Algorithm.HMAC256(JwtProperties.SECRET)))
			.refreshToken(JwtProperties.REFRESH_PREFIX + JWT.create()
				.withSubject(user.getUserKey())
				.withIssuer(JwtProperties.ISS)
				.withExpiresAt(new Date(System.currentTimeMillis() - 1000000))
				.sign(Algorithm.HMAC256(JwtProperties.SECRET)))
			.build();

		user.setSalt(expiredRefreshToken.getRefreshToken());
		userRepository.save(user);
		HttpHeaders headers = createHeaders(expiredRefreshToken.getJwtToken(),
			expiredRefreshToken.getRefreshToken());
		ResponseEntity<String> response = getResponse(headers);
		DocumentContext dc = JsonPath.parse(response.getBody());
		System.out.println(response.getBody());
		Integer status = dc.read("$.status");
		String msg = dc.read("$.msg");
		String responseToken = dc.read("$.data.refreshToken");
		assertThat(status).isEqualTo(200);
		assertThat(msg).isEqualTo("jwt 체크완료");
		assertThat(expiredRefreshToken.getRefreshToken()).isNotEqualTo(responseToken);
		assertThat(userRepository.findByUserKey(user.getUserKey()).get().getSalt()).isEqualTo(
			responseToken);

	}
}
