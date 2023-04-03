package com.jobseeckerstudio.bmm522.user.service.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class GetEmailResponse {

    private String email;

    @Builder
    public GetEmailResponse(String email){
        this.email = email;
    }
}
