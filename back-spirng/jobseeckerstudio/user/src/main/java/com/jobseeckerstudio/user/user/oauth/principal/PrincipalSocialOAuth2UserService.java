package com.jobseeckerstudio.user.user.oauth.principal;

import com.jobseeckerstudio.user.global.exception.NotFoundSocialInfoException;
import com.jobseeckerstudio.user.user.auth.principal.mapper.PrincipalMapper;
import com.jobseeckerstudio.user.user.entity.user.User;
import com.jobseeckerstudio.user.user.oauth.info.GoogleUserInfo;
import com.jobseeckerstudio.user.user.oauth.info.KakaoUserInfo;
import com.jobseeckerstudio.user.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.user.service.user.CreateUserService;
import com.jobseeckerstudio.user.user.service.user.ReadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalSocialOAuth2UserService extends DefaultOAuth2UserService {


    private final ReadUserService readUserService;

    private final CreateUserService createUserService;



    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        SocialUserInfo socialUserInfo = getSocialUserInfo(userRequest, oAuth2User);

        User user = getUser(socialUserInfo);

        return PrincipalMapper.toPrincipalDetailsWithUserInfo(user, oAuth2User.getAttributes());
    }


    private SocialUserInfo getSocialUserInfo(OAuth2UserRequest userRequest ,OAuth2User oAuth2User) {
        switch (userRequest.getClientRegistration().getRegistrationId()) {
            case "google" : return new GoogleUserInfo(oAuth2User.getAttributes());
            case "kakao" : return new KakaoUserInfo(oAuth2User.getAttributes());
            default:
                throw new NotFoundSocialInfoException("등록되지 않은 social 정보입니다.");
        }
    }

    private User getUser(SocialUserInfo socialUserInfo) {
        return readUserService.findByUserKeyWhenSocialLogin(socialUserInfo).orElseGet(()->{
           return createUserService.saveWhenSocialLogin(socialUserInfo);
        });
    }
}
