package com.jobseeckerstudio.user.e2e;



import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.encryption.Encryptor;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-dev.yml")
public class GetEmailE2eTest {

    @Autowired
    private TestRestTemplate rt;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void readyData(){
        User user= User.builder()
            .userKey("test_user")
            .email(Encryptor.encrypt("test_email@test.com"))
            .build();
        JwtToken jwtToken = JwtMaker.create(user);
        user.setSalt(jwtToken.getRefreshToken());
        userRepository.save(user);
    }

    @Test
    @DisplayName("이메일 가져오기 정상적인 요청")
    public void getEmailTestWhenSuccess() {
        String encryptedEmail = Encryptor.encrypt("test_email@test.com");
        User user = User.builder().userKey("test_user").email(encryptedEmail).build();
        JwtToken jwtToken = JwtMaker.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_JWT,  jwtToken.getJwtToken());
        headers.set(JwtProperties.HEADER_REFRESH, jwtToken.getRefreshToken());

        ResponseEntity<String> response = rt.exchange("/api/v1/email", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        DocumentContext dc = JsonPath.parse(response.getBody());
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
        String encryptedEmail = Encryptor.encrypt("test_email@test.com");
        User user = User.builder().userKey("test_user").email(encryptedEmail).build();
        JwtToken jwtToken = JwtMaker.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_JWT,  null);
        headers.set(JwtProperties.HEADER_REFRESH, jwtToken.getRefreshToken());

        ResponseEntity<String> response = rt.exchange("/api/v1/email", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        System.out.println(response.getBody());
        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer status = dc.read("$.status");
        String errorName = dc.read("$.errorName");
        assertThat(status).isEqualTo(401);
        assertThat(errorName).isEqualTo("NotFoundTokenFromHeaderException");
    }

    @Test
    @DisplayName("RefreshToken 없이 요청")
    public void getEmailTestWhenNotHaveRefreshToken() {
        String encryptedEmail = Encryptor.encrypt("test_email@test.com");
        User user = User.builder().userKey("test_user").email(encryptedEmail).build();
        JwtToken jwtToken = JwtMaker.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_JWT,  jwtToken.getJwtToken());
        headers.set(JwtProperties.HEADER_REFRESH, null);

        ResponseEntity<String> response = rt.exchange("/api/v1/email", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer status = dc.read("$.status");
        String errorName = dc.read("$.errorName");
        assertThat(status).isEqualTo(401);
        assertThat(errorName).isEqualTo("NotFoundTokenFromHeaderException");
    }

    @Test
    @DisplayName("등록되지 않은 유저 요청")
    public void getEmailTestWhenUnregisteredUser() {
        String encryptedEmail = Encryptor.encrypt("test_email@test.com");
        User user = User.builder().userKey("rrrr").email(encryptedEmail).build();
        JwtToken jwtToken = JwtMaker.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_JWT,  jwtToken.getJwtToken());
        headers.set(JwtProperties.HEADER_REFRESH, jwtToken.getRefreshToken());

        ResponseEntity<String> response = rt.exchange("/api/v1/email", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        DocumentContext dc = JsonPath.parse(response.getBody());
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
        String encryptedEmail = Encryptor.encrypt("test_email@test.com");
        User user = User.builder().userKey("test_user").email(encryptedEmail).build();
        JwtToken jwtToken = JwtMaker.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_JWT,  "testToken");
        headers.set(JwtProperties.HEADER_REFRESH, jwtToken.getRefreshToken());

        ResponseEntity<String> response = rt.exchange("/api/v1/email", HttpMethod.GET, new HttpEntity<>(headers), String.class);
        System.out.println(response.getBody());
        DocumentContext dc = JsonPath.parse(response.getBody());
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
        String encryptedEmail = Encryptor.encrypt("test_email@test.com");
        User user = User.builder().userKey("test_user").email(encryptedEmail).build();
        JwtToken jwtToken = JwtMaker.create(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set(JwtProperties.HEADER_JWT,  jwtToken.getRefreshToken());
        headers.set(JwtProperties.HEADER_REFRESH,"test");

        ResponseEntity<String> response = rt.exchange("/api/v1/email", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        DocumentContext dc = JsonPath.parse(response.getBody());
        Integer status = dc.read("$.status");
        String msg = dc.read("$.msg");
        String errorName = dc.read("$.errorName");
        assertThat(status).isEqualTo(401);
        assertThat(msg).isEqualTo("잘못된 토큰 정보입니다.");
        assertThat(errorName).isEqualTo("InvalidTokenException");
    }


}
