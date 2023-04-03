package com.jobseeckerstudio.bmm522.user.controller.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonResponse<T> {

    private HttpStatus status;
    private String msg;
    private T data;
    private String errorName;

    // 정상 요청
    @Builder
    public CommonResponse(HttpStatus status, String msg, T data, String errorName ){
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.errorName = errorName;
    }


    // 비 정상 요청
    @Builder
    public CommonResponse(HttpStatus status, String msg, String errorName) {
        this.status = status;
        this.msg = msg;
        this.errorName = errorName;
    }

}
