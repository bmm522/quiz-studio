package com.jobseeckerstudio.bmm522.user.service.user.Impl;

import com.jobseeckerstudio.bmm522.user.encryption.hashcode.HashCodeMaker;
import com.jobseeckerstudio.bmm522.user.entity.salt.Salt;
import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.entity.user.repository.UserRepository;
import com.jobseeckerstudio.bmm522.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.bmm522.user.service.user.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;
    private final HashCodeMaker hashCodeMaker;

    @Override
    public User saveWhenSocialLogin(SocialUserInfo socialUserInfo, String salt) {
        User user = socialUserInfo.toUserEntity();
        Salt saltEntity = Salt.builder().salt(salt).build();
        System.out.println(user);
        System.out.println(saltEntity);
        user.addSalt(saltEntity);
        user.setEmailWithEncryption(hashCodeMaker.getHashCode(salt,user.getEmail()));
        return userRepository.save(user);
    }
}
