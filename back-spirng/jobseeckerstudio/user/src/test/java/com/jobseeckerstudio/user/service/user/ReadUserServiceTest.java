//package com.jobseeckerstudio.user.service.user;
//
//import com.jobseeckerstudio.user.encryption.Decryptor;
//import com.jobseeckerstudio.user.encryption.Encryptor;
//import com.jobseeckerstudio.user.domain.user.User;
//import com.jobseeckerstudio.user.jwt.dto.JwtToken;
//import com.jobseeckerstudio.user.oauth.info.GoogleUserInfo;
//import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
//import com.jobseeckerstudio.user.repository.user.UserRepository;
//import com.jobseeckerstudio.user.service.ReadUserService;
//import com.jobseeckerstudio.user.service.dto.FindUserWhenSocialLoginRequest;
//import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
//import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
//import com.jobseeckerstudio.user.service.mapper.UserServiceMapper;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//public class ReadUserServiceTest {
//
//    @Mock
//    private UserServiceMapper mapper;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private Encryptor encryptor;
//
//    private ReadUserService readUserService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        readUserService = new ReadUserService(userRepository);
//    }
//
//    @Test
//    void findByUserKeyWhenSocialLoginTest() {
//        // given
//        Map<String, Object> userInfoMap = new HashMap<>();
//        userInfoMap.put("sub", "test_user_key");
//        userInfoMap.put("email", "test@example.com");
//        SocialUserInfo socialUserInfo = new GoogleUserInfo(userInfoMap);
//        FindUserWhenSocialLoginRequest dto = new FindUserWhenSocialLoginRequest("test_user_key");
//        User user = new User();
//
//        when(mapper.toFindUserWhenSocialLoginRequest(socialUserInfo)).thenReturn(dto);
//        when(userRepository.findByUserKey(anyString())).thenReturn(Optional.of(user));
//
//        // when
//        Optional<User> result = readUserService.findByUserKeyWhenSocialLogin(socialUserInfo);
//
//        // then
//        Assertions.assertEquals(result.get(), user);
//    }
//
//    @Test
//    void findByUserKeyTest() {
//        // given
//        String userKey = "testUserKey";
//        User user = new User();
//
//        when(userRepository.findByUserKey(userKey)).thenReturn(Optional.of(user));
//
//        // when
//        Optional<User> result = readUserService.findByUserKey(userKey);
//
//        // then
//        Assertions.assertEquals(result.get(), user);
//    }
//
//    @Test
//    void getEmailTest() {
//        // given
//        String userKey = "test_user_key";
//        String email = "test@example.com";
//        JwtToken jwtToken = JwtToken.builder().jwtToken("test_jwt_token").refreshToken("test_refresh_token").build();
//        GetEmailRequest dto = GetEmailRequest.builder().userKey(userKey).build();
//        User user = User.builder().userKey(userKey).email(email).build();
//        GetEmailResponse expectedResponse = new GetEmailResponse(email);
//
//        when(mapper.toGetEmailRequest(jwtToken)).thenReturn(dto);
//        when(userRepository.findByUserKey(anyString())).thenReturn(Optional.of(user));
//
//        when(Decryptor.decrypt(user.getEmail())).thenReturn(email);
//        when(mapper.toGetEmailResponse(email)).thenReturn(expectedResponse);
//
//        // when
//        GetEmailResponse result = readUserService.getEmail(jwtToken);
//
//        // then
//        Assertions.assertEquals(expectedResponse, result);
//    }
//}
