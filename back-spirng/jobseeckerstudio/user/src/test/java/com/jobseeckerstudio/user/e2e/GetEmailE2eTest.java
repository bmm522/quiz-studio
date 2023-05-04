package com.jobseeckerstudio.user.e2e;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.encrypt.Encryptor;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-dev.yml")
@DisplayName("이메일 불러오기 e2e 테스트")
public class GetEmailE2eTest {

    @Autowired
    private TestRestTemplate rt;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private JwtToken jwtToken;


    @BeforeEach
    public void readyData() {
        testUser = User.builder()
            .userKey("test_user")
            .email(Encryptor.encrypt("test_email@test.com"))
            .build();
        jwtToken = JwtMaker.create(testUser);
        testUser.setSalt(jwtToken.getRefreshToken());
        userRepository.save(testUser);
    }

    private HttpHeaders createHeaders(String jwtToken, String refreshToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_JWT, jwtToken);
        headers.set(JwtProperties.HEADER_REFRESH, refreshToken);
        return headers;
    }

    private DocumentContext executeGetEmailRequest(HttpHeaders headers) {
        ResponseEntity<String> response = rt.exchange("/api/v1/email", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        return JsonPath.parse(response.getBody());
    }

    @Test
    @DisplayName("이메일 가져오기 정상적인 요청")
    public void getEmailTestWhenSuccess() {
        HttpHeaders headers = createHeaders(jwtToken.getJwtToken(), jwtToken.getRefreshToken());
        DocumentContext dc = executeGetEmailRequest(headers);

        Integer status = dc.read("$.status");
        String msg = dc.read("$.msg");
        String email = dc.read("$.data.email");
        assertThat(status).isEqualTo(200);
        assertThat(msg).isEqualTo("이메일 불러오기 성공");
        assertThat(email).isEqualTo("test_email@test.com");
    }

    @Test
    @DisplayName("AccessToken 없이 요청")
    public void getEmailTestWhenNotHaveJwtToken() {
        HttpHeaders headers = createHeaders(null, jwtToken.getRefreshToken());
        DocumentContext dc = executeGetEmailRequest(headers);

        Integer status = dc.read("$.status");
        String errorName = dc.read("$.errorName");
        assertThat(status).isEqualTo(401);
        assertThat(errorName).isEqualTo("NotFoundTokenFromHeaderException");
    }

    @Test
    @DisplayName("RefreshToken 없이 요청")
    public void getEmailTestWhenNotHaveRefreshToken() {
        HttpHeaders headers = createHeaders(jwtToken.getJwtToken(), null);
        DocumentContext dc = executeGetEmailRequest(headers);

        Integer status = dc.read("$.status");
        String errorName = dc.read("$.errorName");

        assertThat(status).isEqualTo(401);
        assertThat(errorName).isEqualTo("NotFoundTokenFromHeaderException");
    }

    @Test
    @DisplayName("등록되지 않은 유저 요청")
    public void getEmailTestWhenUnregisteredUser() {
        User unregisteredUser = User.builder().userKey("rrrr").email(Encryptor.encrypt("test_email@test.com")).build();
        JwtToken jwtTokenForUnregisteredUser = JwtMaker.create(unregisteredUser);
        HttpHeaders headers = createHeaders(jwtTokenForUnregisteredUser.getJwtToken(), jwtTokenForUnregisteredUser.getRefreshToken());
        DocumentContext dc = executeGetEmailRequest(headers);

        Integer status = dc.read("$.status");
        String msg = dc.read("$.msg");
        String errorName = dc.read("$.errorName");
        assertThat(status).isEqualTo(500);
        assertThat(msg).isEqualTo("userKey에 해당하는 유저 정보가 없습니다.");
        assertThat(errorName).isEqualTo("NotFoundUserException");
    }

    @Test
    @DisplayName("유효하지 않은 accessToken으로 요청")
    public void getEmailTestWhenInvalidAccessToken() {
        HttpHeaders headers = createHeaders("testToken", jwtToken.getRefreshToken());
        DocumentContext dc = executeGetEmailRequest(headers);

        Integer status = dc.read("$.status");
        String msg = dc.read("$.msg");
        String errorName = dc.read("$.errorName");

        assertThat(status).isEqualTo(401);
        assertThat(msg).isEqualTo("잘못된 토큰 정보입니다.");
        assertThat(errorName).isEqualTo("InvalidTokenException");
    }

    @Test
    @DisplayName("유효하지 않은 refreshToken으로 요청")
    public void getEmailTestWhenInvalidRefreshToken() {
        HttpHeaders headers = createHeaders(jwtToken.getJwtToken(), "test");
        DocumentContext dc = executeGetEmailRequest(headers);

        Integer status = dc.read("$.status");
        String msg = dc.read("$.msg");
        String errorName = dc.read("$.errorName");

        assertThat(status).isEqualTo(401);
        assertThat(msg ).isEqualTo("잘못된 토큰 정보입니다.");
        assertThat(errorName).isEqualTo("InvalidTokenException");
    }

}
