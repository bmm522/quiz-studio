package com.jobseeckerstudio.user.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetEmailRequest {

    private String userKey;

    @Builder
    public GetEmailRequest(String userKey){
        this.userKey = userKey;
    }
}
