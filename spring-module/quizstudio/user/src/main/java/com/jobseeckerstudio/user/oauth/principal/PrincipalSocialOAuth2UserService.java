package com.jobseeckerstudio.user.oauth.principal;

import com.jobseeckerstudio.user.auth.principal.mapper.PrincipalMapper;
import com.jobseeckerstudio.user.exception.NotFoundSocialInfoException;
import com.jobseeckerstudio.user.oauth.info.GoogleUserInfo;
import com.jobseeckerstudio.user.oauth.info.KakaoUserInfo;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class PrincipalSocialOAuth2UserService extends DefaultOAuth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		log.info("service 들어옴");
		OAuth2User oAuth2User = super.loadUser(userRequest);

		SocialUserInfo socialUserInfo = getSocialUserInfo(userRequest, oAuth2User);

		return PrincipalMapper.toPrincipalDetailsWithUserInfo(socialUserInfo.toUserEntity(),
			oAuth2User.getAttributes());
	}

	/**
	 * 소셜 로그인 공급자에 따라 SocialUserInfo를 가져옵니다.
	 *
	 * @param userRequest OAuth2UserRequest 객체
	 * @param oAuth2User  OAuth2User 객체
	 * @return SocialUserInfo 객체
	 * @throws NotFoundSocialInfoException 등록되지 않은 소셜 정보인 경우 발생하는 예외
	 */
	private SocialUserInfo getSocialUserInfo(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		log.info("service : " + userRequest.toString());
		switch (userRequest.getClientRegistration().getRegistrationId()) {
			case "google":
				return new GoogleUserInfo(oAuth2User.getAttributes());
			case "kakao":
				return new KakaoUserInfo(oAuth2User.getAttributes());
			default:
				throw new NotFoundSocialInfoException("등록되지 않은 social 정보입니다.");
		}
	}

}
