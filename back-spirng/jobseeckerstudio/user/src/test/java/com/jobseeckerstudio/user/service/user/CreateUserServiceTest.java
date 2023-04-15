package com.jobseeckerstudio.user.service.user;

import com.jobseeckerstudio.user.test.Draw;
import com.jobseeckerstudio.user.encryption.Encryptor;
import com.jobseeckerstudio.user.entity.User;
import com.jobseeckerstudio.user.entity.repository.UserRepository;
import com.jobseeckerstudio.user.oauth.info.GoogleUserInfo;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateUserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private Encryptor encryptor;

    @Mock
    private Draw draw;

    private CreateUserServiceImpl createUserService;

    @BeforeEach
    void setUp() {
        createUserService = new CreateUserServiceImpl(userRepository);
    }

    @Test
    void saveWhenSocialLoginTest() {
        // given
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("sub", "test_user_key");
        userInfoMap.put("email", "test@example.com");
        SocialUserInfo socialUserInfo = new GoogleUserInfo(userInfoMap);
        User user = User.builder().email("testEncryptedEmail").build();

        when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        User savedUser = createUserService.saveWhenSocialLogin(socialUserInfo);

        // then
        assertEquals(savedUser.getEmail(), "testEncryptedEmail");
    }

    @Test
    void saveWhenSocialLoginWithDrawTest() {
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("sub", "test");
        userInfoMap.put("email", "test@example.com");
        SocialUserInfo socialUserInfo = new GoogleUserInfo(userInfoMap);
        User user = User.builder().userKey("test").build();

        when(userRepository.save(any())).thenReturn(user);

        User savedUser = createUserService.saveWhenSocialLogin(socialUserInfo);
        System.out.println(savedUser);
        assertEquals(savedUser.getUserKey(), "test");
    }
}
