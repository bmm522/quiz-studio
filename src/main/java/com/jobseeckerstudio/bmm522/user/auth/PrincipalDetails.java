package com.jobseeckerstudio.bmm522.user.auth;

import com.jobseeckerstudio.bmm522.user.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;

public class PrincipalDetails implements UserDetails {

    private User user;

    private Map<String, Object> userInfoMap;

    public PrincipalDetails(User user){
        this.user = user;
    }

    public PrincipalDetails(User user, Map<String, Object> userInfoMap){
        this.user = user;
        this.userInfoMap = userInfoMap;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserKey();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
