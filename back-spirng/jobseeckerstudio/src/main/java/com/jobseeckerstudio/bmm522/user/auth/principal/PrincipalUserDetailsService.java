package com.jobseeckerstudio.bmm522.user.auth.principal;

import com.jobseeckerstudio.bmm522.global.exception.LoginFailException;
import com.jobseeckerstudio.bmm522.user.auth.principal.mapper.PrincipalMapper;
import com.jobseeckerstudio.bmm522.user.entity.user.User;
import com.jobseeckerstudio.bmm522.user.repository.UserQueryRepository;
import com.jobseeckerstudio.bmm522.user.service.user.ReadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalUserDetailsService implements UserDetailsService {
    private final ReadUserService readUserService;
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = readUserService.findByUserKey(username)
            .orElseThrow(() -> new LoginFailException("해당 유저 정보를 찾을 수 없습니다."));
        return PrincipalMapper.toPrincipalDetailsWithOutUserInfo(user);
    }

}
