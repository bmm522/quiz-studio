package com.jobseeckerstudio.bmm522.service.user;

import com.jobseeckerstudio.bmm522.user.encryption.Encryptor;
import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.oauth.info.GoogleUserInfo;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.repository.user.UserQueryRepository;
import com.jobseeckerstudio.bmm522.user.service.user.Impl.ReadUserServiceImpl;
import com.jobseeckerstudio.bmm522.user.service.user.dto.FindUserWhenSocialLoginRequest;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailRequest;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailResponse;
import com.jobseeckerstudio.bmm522.user.service.user.mapper.UserServiceMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class ReadUserServiceTest {

    @Mock
    private UserServiceMapper mapper;

    @Mock
    private UserQueryRepository userQueryRepository;

    @Mock
    private Encryptor encryptor;

    private ReadUserServiceImpl readUserService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        readUserService = new ReadUserServiceImpl(mapper, userQueryRepository, encryptor);
    }

    @Test
    void findByUserKeyWhenSocialLoginTest() {
        // given
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("sub", "test_user_key");
        userInfoMap.put("email", "test@example.com");
        SocialUserInfo socialUserInfo = new GoogleUserInfo(userInfoMap);
        FindUserWhenSocialLoginRequest dto = new FindUserWhenSocialLoginRequest("test_user_key");
        User user = new User();

        when(mapper.toFindUserWhenSocialLoginRequest(socialUserInfo)).thenReturn(dto);
        when(userQueryRepository.findByUserKey(anyString())).thenReturn(Optional.of(user));

        // when
        Optional<User> result = readUserService.findByUserKeyWhenSocialLogin(socialUserInfo);

        // then
        Assertions.assertEquals(result.get(), user);
    }

    @Test
    void findByUserKeyTest() {
        // given
        String userKey = "testUserKey";
        User user = new User();

        when(userQueryRepository.findByUserKey(userKey)).thenReturn(Optional.of(user));

        // when
        Optional<User> result = readUserService.findByUserKey(userKey);

        // then
        Assertions.assertEquals(result.get(), user);
    }

    @Test
    void getEmailTest() {
        // given
        String userKey = "test_user_key";
        String email = "test@example.com";
        JwtToken jwtToken = JwtToken.builder().jwtToken("test_jwt_token").refreshToken("test_refresh_token").build();
        GetEmailRequest dto = GetEmailRequest.builder().userKey(userKey).build();
        User user = User.builder().userKey(userKey).email(email).build();
        GetEmailResponse expectedResponse = new GetEmailResponse(email);

        when(mapper.toGetEmailRequest(jwtToken)).thenReturn(dto);
        when(userQueryRepository.findByUserKey(anyString())).thenReturn(Optional.of(user));

        when(encryptor.decrypt(user.getEmail())).thenReturn(email);
        when(mapper.toGetEmailResponse(email)).thenReturn(expectedResponse);

        // when
        GetEmailResponse result = readUserService.getEmail(jwtToken);

        // then
        Assertions.assertEquals(expectedResponse, result);
    }
}
