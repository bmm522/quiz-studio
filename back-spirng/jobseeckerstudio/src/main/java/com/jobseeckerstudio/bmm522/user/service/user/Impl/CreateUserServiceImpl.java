package com.jobseeckerstudio.bmm522.user.service.user.Impl;

import com.jobseeckerstudio.bmm522.user.encryption.Encryptor;
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
    private final Encryptor encryptor;

    @Override
    public User saveWhenSocialLogin(SocialUserInfo socialUserInfo) {
        User user = socialUserInfo.toUserEntity();
        setEmailWithEncryption(user);
        return userRepository.save(user);
    }

    private void setEmailWithEncryption(User user) {
        user.setEmailWithEncryption(encryptor.encrypt(user.getEmail()));
    }

}
