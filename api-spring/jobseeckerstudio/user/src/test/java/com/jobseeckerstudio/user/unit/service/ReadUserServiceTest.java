package com.jobseeckerstudio.user.unit.service;


import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.encrypt.Encryptor;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import com.jobseeckerstudio.user.service.ReadUserService;
import com.jobseeckerstudio.user.service.dto.GetEmailRequest;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
import com.jobseeckerstudio.user.service.mapper.UserServiceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.yml")
public class ReadUserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ReadUserService readUserService;

    @Mock
    private UserServiceMapper userServiceMapper;


    @Test
    @DisplayName("getEmail 테스트")
    public void getEmailTest() {
        //given
        String userKey = "testUser";
        String encryptedEmail = Encryptor.encrypt("test@test.com");
        User user = User.builder().userKey(userKey).email(encryptedEmail).build();
        JwtToken jwtToken = JwtMaker.create(user);
        GetEmailRequest getEmailRequest = new GetEmailRequest(userKey);

        when(userRepository.findByUserKey(getEmailRequest.getUserKey())).thenReturn(Optional.of(user));

//        //when
        GetEmailResponse result = readUserService.getEmail(jwtToken);

        //then
        assertThat(result.getEmail()).isEqualTo("test@test.com");
    }


}
