package com.jobseeckerstudio.user.service;

import com.jobseeckerstudio.user.encryption.Encryptor;
import com.jobseeckerstudio.user.entity.User;
import com.jobseeckerstudio.user.entity.repository.UserRepository;
import com.jobseeckerstudio.user.oauth.info.SocialUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;


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
