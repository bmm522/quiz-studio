package com.jobseeckerstudio.bmm522.user.auth.principal;

import com.jobseeckerstudio.bmm522.global.exception.LoginFailException;
import com.jobseeckerstudio.bmm522.global.exception.NotFoundSocialInfoException;
import com.jobseeckerstudio.bmm522.user.auth.principal.mapper.PrincipalMapper;
import com.jobseeckerstudio.bmm522.user.entity.User;
import com.jobseeckerstudio.bmm522.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalUserDetailsService implements UserDetailsService {

    private final UserQueryRepository userQueryRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userQueryRepository.findByUserKey(username)
            .orElseThrow(() -> new LoginFailException("해당 유저 정보를 찾을 수 없습니다."));
        return PrincipalMapper.toPrincipalDetailsWithOutUserInfo(user);
    }

}
