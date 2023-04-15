package com.jobseeckerstudio.user.user.service.user.Impl;

import com.jobseeckerstudio.user.user.encryption.Encryptor;
import com.jobseeckerstudio.user.user.entity.user.User;
import com.jobseeckerstudio.user.user.entity.user.repository.UserRepository;
import com.jobseeckerstudio.user.user.oauth.info.SocialUserInfo;
import com.jobseeckerstudio.user.user.service.user.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserServiceImpl implements CreateUserService {

    private final UserRepository userRepository;

    @Override
    public User saveWhenSocialLogin(SocialUserInfo socialUserInfo) {
        User user = socialUserInfo.toUserEntity();
        setEmailWithEncryption(user);
        return userRepository.save(user);
    }

    private User setEmailWithEncryption(User user) {
        user.setEmailWithEncryption(Encryptor.encrypt(user.getEmail()));
        return user;
    }




}
