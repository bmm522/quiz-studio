package com.jobseeckerstudio.user.config;


import com.jobseeckerstudio.user.auth.filter.LoginAuthenticationFilter;
import com.jobseeckerstudio.user.jwt.filter.JwtAuthorizationFilter;
import com.jobseeckerstudio.user.oauth.handler.SocialLoginSuccessHandler;
import com.jobseeckerstudio.user.oauth.principal.PrincipalSocialOAuth2UserService;
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

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.anyRequest().permitAll();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.addFilter(corsConfig.corsFilter())
			.formLogin().disable()
			.httpBasic().disable()
			.addFilter(new LoginAuthenticationFilter(authenticationManager()))
			.addFilter(new JwtAuthorizationFilter(authenticationManager()))
			.oauth2Login()
			.userInfoEndpoint()
			.userService(principalSocialOAuth2UserService)
			.and()
			.successHandler(socialLoginSuccessHandler);


	}
}
