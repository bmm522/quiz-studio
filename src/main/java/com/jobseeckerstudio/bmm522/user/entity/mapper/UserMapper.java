package com.jobseeckerstudio.bmm522.user.entity.mapper;

import com.jobseeckerstudio.bmm522.user.entity.User;

import javax.servlet.http.HttpServletRequest;

public class UserMapper {

    public static User toUserFromRequest(HttpServletRequest request) {
        return User.builder()
                .userKey((String) request.getAttribute("userKey"))
                .password("null")
                .build();
    }
}
