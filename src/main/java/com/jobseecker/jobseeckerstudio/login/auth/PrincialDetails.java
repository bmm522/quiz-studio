package com.jobseecker.jobseeckerstudio.login.auth;

import com.jobseecker.jobseeckerstudio.login.repository.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public class PrincialDetails implements UserDetails {

    private User user;

    private Map<String, Object> userInfoMap;

    public PrincialDetails(User user){
        this.user = user;
    }

    public PrincialDetails(User user, Map<String, Object> userInfoMap){
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
