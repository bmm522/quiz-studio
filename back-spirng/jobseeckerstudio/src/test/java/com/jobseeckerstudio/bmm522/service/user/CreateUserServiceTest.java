package com.jobseeckerstudio.bmm522.service.user;

import com.jobseeckerstudio.bmm522.user.encryption.Encryptor;
import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.entity.user.repository.UserRepository;
import com.jobseeckerstudio.bmm522.user.oauth.info.GoogleUserInfo;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.service.user.Impl.CreateUserServiceImpl;
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

    private CreateUserServiceImpl createUserService;

    @BeforeEach
    void setUp() {
        createUserService = new CreateUserServiceImpl(userRepository, encryptor);
    }

    @Test
    void saveWhenSocialLoginTest() {
        // given
        Map<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("sub", "test_user_key");
        userInfoMap.put("email", "test@example.com");
        SocialUserInfo socialUserInfo = new GoogleUserInfo(userInfoMap);
        User user = User.builder().email("testEncryptedEmail").build();

        when(encryptor.encrypt(any())).thenReturn("testEncryptedEmail");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // when
        User savedUser = createUserService.saveWhenSocialLogin(socialUserInfo);

        // then
        assertEquals(savedUser.getEmail(), "testEncryptedEmail");
    }
}
