package com.jobseeckerstudio.bmm522.user.service.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetEmailRequest {

    private String authorization;
    private String refreshToken;

    @Builder
    public GetEmailRequest(String authorization, String refreshToken){
        this.authorization = authorization;
        this.refreshToken = refreshToken;
    }
}
