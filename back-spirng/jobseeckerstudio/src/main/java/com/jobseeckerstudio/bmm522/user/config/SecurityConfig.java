package com.jobseeckerstudio.bmm522.user.config;


import com.jobseeckerstudio.bmm522.global.config.CorsConfig;
import com.jobseeckerstudio.bmm522.user.auth.filter.LoginAuthenticationFilter;
import com.jobseeckerstudio.bmm522.user.jwt.JwtTokenFactory;
import com.jobseeckerstudio.bmm522.user.jwt.filter.JwtAuthorizationFilter;
import com.jobseeckerstudio.bmm522.user.jwt.mapper.JwtMapper;
import com.jobseeckerstudio.bmm522.user.oauth.handler.SocialLoginSuccessHandler;
import com.jobseeckerstudio.bmm522.user.oauth.principal.PrincipalSocialOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PrincipalSocialOAuth2UserService principalSocialOAuth2UserService;

    private final CorsConfig corsConfig;

    private final SocialLoginSuccessHandler socialLoginSuccessHandler;

    private final JwtTokenFactory jwtTokenFactory;

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
            .anyRequest().permitAll();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .addFilter(corsConfig.corsFilter())
            .formLogin().disable()
            .httpBasic().disable()
            .addFilter(new LoginAuthenticationFilter(authenticationManager(), jwtTokenFactory))
            .addFilter(new JwtAuthorizationFilter(authenticationManager()))
            .oauth2Login()
            .userInfoEndpoint()
            .userService(principalSocialOAuth2UserService)
            .and()
            .successHandler(socialLoginSuccessHandler);


    }
}
