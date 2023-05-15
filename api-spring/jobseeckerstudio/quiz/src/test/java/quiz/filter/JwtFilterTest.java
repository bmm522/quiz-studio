package quiz.filter;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import quiz.exception.ExpiredTokenException;
import quiz.exception.InvalidTokenException;
import quiz.exception.NullUserKeyFromJwtTokenException;
import quiz.properties.JwtProperties;

@SpringBootTest
public class JwtFilterTest {

    @InjectMocks
    private JwtFilter jwtFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain chain;

    @BeforeEach
    public void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        chain = new MockFilterChain();
    }

    @Test
    @DisplayName("잘못된 jwt토큰 요청")
    void filterTestWhenInvalidToken() {
        request.addHeader(JwtProperties.HEADER_JWT, "invalid.jwt.token");

        Exception exception = assertThrows(InvalidTokenException.class, () -> {
            jwtFilter.doFilter(request, response, chain);
        });

        assertThat(exception.getMessage()).isEqualTo("잘못된 토큰 정보입니다.");
    }

    @Test
    @DisplayName("유효시간 지난 jwt토큰 요청")
    void filterTestWhenHaveToken() {
        String jwt = JwtProperties.TOKEN_PREFIX + JWT.create()
            .withSubject("testUser")
            .withIssuer(JwtProperties.ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() - 10000000))
            .withClaim("userKey", "testUser")
            .sign(Algorithm.HMAC256(JwtProperties.SECRET));

        request.addHeader(JwtProperties.HEADER_JWT, jwt);

        Exception exception = assertThrows(ExpiredTokenException.class, () -> {
            jwtFilter.doFilter(request, response, chain);
        });

        assertThat(exception.getMessage()).isEqualTo("유효시간이 지난 토큰입니다.");
    }

    @Test
    @DisplayName("claim에 userkey가 없을 때 요청")
    void filterTestWhenNotHaveUserKeyToken() {
        String jwt = JwtProperties.TOKEN_PREFIX + JWT.create()
            .withSubject("testUser")
            .withIssuer(JwtProperties.ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() + 10000000))
            .sign(Algorithm.HMAC256(JwtProperties.SECRET));

        request.addHeader(JwtProperties.HEADER_JWT, jwt);

        Exception exception = assertThrows(NullUserKeyFromJwtTokenException.class, () -> {
            jwtFilter.doFilter(request, response, chain);
        });

        assertThat(exception.getMessage()).isEqualTo("jwt토큰으로부터 userKey를 찾을 수 없습니다.");
    }

    @Test
    @DisplayName("정상적인 요청")
    void filterTest() throws ServletException, IOException {
        String jwt = JwtProperties.TOKEN_PREFIX + JWT.create()
            .withSubject("testUser")
            .withIssuer(JwtProperties.ISS)
            .withExpiresAt(new Date(System.currentTimeMillis() + 10000000))
            .withClaim("userKey", "testUser")
            .sign(Algorithm.HMAC256(JwtProperties.SECRET));

        request.addHeader(JwtProperties.HEADER_JWT, jwt);
        jwtFilter.doFilter(request, response, chain);

        assertThat(request.getAttribute("userKey")).isEqualTo("testUser");
    }


}
