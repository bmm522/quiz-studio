package com.jobseeckerstudio.bmm522.user.auth;

import com.jobseeckerstudio.bmm522.user.auth.mapper.AuthMapper;
import com.jobseeckerstudio.bmm522.user.entity.User;
import com.jobseeckerstudio.bmm522.user.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalUserDetailsService implements UserDetailsService {

    private final UserQueryRepository userQueryRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userQueryRepository.findByUserKey(username);
        return AuthMapper.toPrincipalDetailsWithOutUserInfo(user);
    }
}
