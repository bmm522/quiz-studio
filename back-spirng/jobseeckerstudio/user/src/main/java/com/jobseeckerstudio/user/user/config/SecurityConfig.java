package com.jobseeckerstudio.user.user.config;


import com.jobseeckerstudio.user.global.config.CorsConfig;
import com.jobseeckerstudio.user.user.auth.filter.LoginAuthenticationFilter;
import com.jobseeckerstudio.user.user.jwt.JwtTokenFactory;
import com.jobseeckerstudio.user.user.jwt.filter.JwtAuthorizationFilter;
import com.jobseeckerstudio.user.user.oauth.handler.SocialLoginSuccessHandler;
import com.jobseeckerstudio.user.user.oauth.principal.PrincipalSocialOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

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
