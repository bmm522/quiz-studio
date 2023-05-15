package com.jobseeckerstudio.user.unit.service;

import com.jobseeckerstudio.user.domain.user.User;
import com.jobseeckerstudio.user.exception.NotFoundSaltException;
import com.jobseeckerstudio.user.jwt.JwtMaker;
import com.jobseeckerstudio.user.jwt.JwtToken;
import com.jobseeckerstudio.user.repository.user.UserRepository;
import com.jobseeckerstudio.user.service.JwtExpiredChecker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@WebAppConfiguration
class JwtExpiredCheckerTest {

    private JwtExpiredChecker jwtExpiredChecker;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtExpiredChecker = new JwtExpiredChecker(userRepository);
    }

    @Test
    @DisplayName("salt 값 없을때 예외처리")
    void testCheckWithNonExistingRefreshToken() {
        // Given
        JwtToken jwtToken = JwtToken.builder()
            .jwtToken("access_token")
            .refreshToken("refresh_token")
            .build();
        when(userRepository.findBySalt(jwtToken.getRefreshToken())).thenReturn(Optional.empty());

        // When/Then
        NotFoundSaltException exception = assertThrows(NotFoundSaltException.class, () -> jwtExpiredChecker.check(jwtToken));
        assertEquals("refreshToken에 해당되는 유저 정보가 없습니다.", exception.getMessage());
        verify(userRepository).findBySalt(jwtToken.getRefreshToken());
    }

    @Test
    @DisplayName("정상적인 상황")
    void testCheckWithNonExpiredToken() {
        // Given
        User user = User.builder()
            .userKey("test_user")
            .build();
        JwtToken jwtToken = JwtMaker.create(user);
        when(userRepository.findBySalt(jwtToken.getRefreshToken())).thenReturn(Optional.of(user));

        // When
        JwtToken result = jwtExpiredChecker.check(jwtToken);

        // Then
        assertEquals(jwtToken.getJwtToken(), result.getJwtToken());
        verify(userRepository).findBySalt(jwtToken.getRefreshToken());
    }

}
