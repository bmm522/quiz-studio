package com.jobseeckerstudio.user.unit.mapper;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.jwt.properties.JwtProperties;
import com.jobseeckerstudio.user.oauth.info.GoogleUserInfo;
import com.jobseeckerstudio.user.oauth.info.KakaoUserInfo;
import com.jobseeckerstudio.user.oauth.info.Provider;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.service.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
import com.jobseeckerstudio.user.service.mapper.UserServiceMapper;

public class UserServiceMapperTest {
    private static final String TEST_SUB = "test_sub";
    private static final String TEST_EMAIL = "test@test.com";
    @Mock
    SocialUserInfo socialUserInfoMock;

    @Test
    @DisplayName("(Google 일 경우)SocialUserInfo 객체로 FindUserWhenSocialLoginRequest 객체 변경 ")
    void toFindUserWhenSocialLoginRequestTestWhenGoogle() {
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("sub", TEST_SUB);
        userInfoMap.put("email", TEST_EMAIL);
        SocialUserInfo googleUserInfo = new GoogleUserInfo(userInfoMap);

        FindUserWhenSocialLoginRequest request = UserServiceMapper.toFindUserWhenSocialLoginRequest(googleUserInfo);

        assertThat(request.getUserKey()).isEqualTo("Google_"+TEST_SUB);
    }

    @Test
    @DisplayName("(Kakao 일 경우)SocialUserInfo 객체로 FindUserWhenSocialLoginRequest 객체 변경 ")
    void toFindUserWhenSocialLoginRequestTest() {
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("id", TEST_SUB);
        Map<String, Object> kakaoAccountMap = new HashMap<>();
        kakaoAccountMap.put("email", TEST_EMAIL);
        userInfoMap.put("kakao_account", kakaoAccountMap);

        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(userInfoMap);

        FindUserWhenSocialLoginRequest request = UserServiceMapper.toFindUserWhenSocialLoginRequest(kakaoUserInfo);

        assertThat(request.getUserKey()).isEqualTo("Kakao_"+TEST_SUB);
    }
    @Test
    @DisplayName("email 문자열로 GetEmailResponse 객체 생성")
    void toGetEmailRequestTest() {
        GetEmailResponse getEmailResponse = UserServiceMapper.toGetEmailResponse(TEST_EMAIL);

        Assertions.assertEquals(TEST_EMAIL, getEmailResponse.getEmail());
    }


}
