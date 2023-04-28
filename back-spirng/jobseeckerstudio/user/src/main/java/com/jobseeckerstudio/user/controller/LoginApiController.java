package com.jobseeckerstudio.user.controller;

import com.jobseeckerstudio.user.controller.dto.CommonResponse;
import com.jobseeckerstudio.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.user.jwt.mapper.JwtMapper;
import com.jobseeckerstudio.user.service.ReNewService;
import com.jobseeckerstudio.user.service.ReadUserService;
import com.jobseeckerstudio.user.service.dto.GetEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/v1")
@Controller
@RequiredArgsConstructor
public class LoginApiController {

    private final ReadUserService readUserService;

    private final ReNewService reNewService;

    @GetMapping("social/login/{social}")
    public String moveSocialLoginForm(@PathVariable("social")String social){
        return "redirect:/oauth2/authorization/"+social;
    }

    @GetMapping("/email")
    public @ResponseBody CommonResponse<?> getEmail(HttpServletRequest request) {
        JwtToken jwtToken = JwtMapper.toJwtToken(request);
        GetEmailResponse dto = readUserService.getEmail(jwtToken);
        return responseHandler(HttpStatus.OK, "이메일 불러오기 성공", dto);
    }

    @GetMapping("re-token")
    public @ResponseBody CommonResponse<?> reNewToken(HttpServletRequest request, HttpServletResponse response) {
        JwtToken jwtToken = JwtMapper.toJwtToken(request);
        JwtToken newToken = reNewService.reNewToken(jwtToken);
        return responseHandler(HttpStatus.OK, "새로운 토큰 발급 성공", newToken);
    }



    private CommonResponse<?> responseHandler(HttpStatus status, String msg, Object data){
        return CommonResponse.builder()
            .status(status)
            .msg(msg)
            .data(data)
            .build();
    }


}
