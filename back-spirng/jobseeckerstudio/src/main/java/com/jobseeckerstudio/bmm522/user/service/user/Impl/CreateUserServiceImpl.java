package com.jobseeckerstudio.bmm522.user.service.user.Impl;

import com.jobseeckerstudio.bmm522.user.encryption.hashcode.HashCodeMaker;
import com.jobseeckerstudio.bmm522.user.encryption.salt.SaltMaker;
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

    private final SaltMaker saltMaker;

    private final HashCodeMaker hashCodeMaker;

    @Override
    public User saveWhenSocialLogin(SocialUserInfo socialUserInfo) {
        User user = socialUserInfo.toUserEntity();
        Salt salt = getSaltEntity();
        user.addSalt(salt);
        user.setEmailWithEncryption(hashCodeMaker.getHashCode(salt.getSalt(),user.getEmail()));
        return userRepository.save(user);
    }

    private Salt getSaltEntity() {
        String salt = saltMaker.getSalt();
        return Salt.builder().salt(salt).build();
    }

}
