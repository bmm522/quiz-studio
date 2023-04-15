package com.jobseeckerstudio.user.user.service.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FindUserWhenSocialLoginRequest {

    private String userKey;

    @Builder
    public FindUserWhenSocialLoginRequest(String userKey){
        this.userKey = userKey;
    }
}
