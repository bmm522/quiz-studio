package com.jobseeckerstudio.bmm522.user.auth.mapper;

import com.jobseeckerstudio.bmm522.user.auth.PrincipalDetails;
import com.jobseeckerstudio.bmm522.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public class AuthMapper {

    public static UserDetails toPrincipalDetailsWithOutUserInfo(User user) {
        return new PrincipalDetails(user);
    }
    public static UserDetails toPrincipalDetailsWithUserInfo(User user, Map<String, Object> userInfoMap) {
        return new PrincipalDetails(user, userInfoMap);
    }
}
