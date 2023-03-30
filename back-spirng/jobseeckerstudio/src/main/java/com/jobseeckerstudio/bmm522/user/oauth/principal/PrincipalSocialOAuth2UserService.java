package com.jobseeckerstudio.bmm522.user.oauth.principal;

import com.jobseeckerstudio.bmm522.global.exception.NotFoundSocialInfoException;
import com.jobseeckerstudio.bmm522.user.auth.principal.mapper.PrincipalMapper;
import com.jobseeckerstudio.bmm522.user.entity.User;
import com.jobseeckerstudio.bmm522.user.entity.repository.UserRepository;
import com.jobseeckerstudio.bmm522.user.oauth.info.GoogleUserInfo;
import com.jobseeckerstudio.bmm522.user.oauth.info.KakaoUserInfo;
import com.jobseeckerstudio.bmm522.user.oauth.info.Provider;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PrincipalSocialOAuth2UserService extends DefaultOAuth2UserService {


    private final UserRepository userRepository;
    private final UserQueryRepository userQueryRepository;

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
        return userQueryRepository.findByUserKey(socialUserInfo.getProvider()+"_"+ socialUserInfo.getUserKey())
            .orElseGet(() -> {
                User user = socialUserInfo.toUserEntity();
                return userRepository.save(user);
            });
    }
}