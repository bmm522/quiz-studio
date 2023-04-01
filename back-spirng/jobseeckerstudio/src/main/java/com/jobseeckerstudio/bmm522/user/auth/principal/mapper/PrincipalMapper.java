package com.jobseeckerstudio.bmm522.user.auth.principal.mapper;

import com.jobseeckerstudio.bmm522.user.auth.principal.PrincipalDetails;
import com.jobseeckerstudio.bmm522.user.entity.user.User;

import java.util.Map;

public class PrincipalMapper {

    public static PrincipalDetails toPrincipalDetailsWithOutUserInfo(User user) {
        return new PrincipalDetails(user);
    }
    public static PrincipalDetails toPrincipalDetailsWithUserInfo(User user, Map<String, Object> userInfoMap) {
        return new PrincipalDetails(user, userInfoMap);
    }
}
