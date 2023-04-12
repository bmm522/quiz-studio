package com.jobseeckerstudio.bmm522.user.controller;

import com.jobseeckerstudio.bmm522.test.Draw;
import com.jobseeckerstudio.bmm522.user.controller.dto.CommonResponse;
import com.jobseeckerstudio.bmm522.user.jwt.dto.JwtToken;
import com.jobseeckerstudio.bmm522.user.jwt.mapper.JwtMapper;
import com.jobseeckerstudio.bmm522.user.service.user.ReadUserService;
import com.jobseeckerstudio.bmm522.user.service.user.dto.GetEmailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1")
@Controller
@RequiredArgsConstructor
public class LoginApiController {

    private final ReadUserService readUserService;

    private final JwtMapper jwtMapper = JwtMapper.getJwtMapper();

    @GetMapping("social/login/{social}")
    public String moveSocialLoginForm(@PathVariable("social")String social){
        return "redirect:/oauth2/authorization/"+social;
    }

    @GetMapping("/email")
    public @ResponseBody CommonResponse<?> getEmail(HttpServletRequest request) {
        JwtToken jwtToken = jwtMapper.toJwtToken(request);
        GetEmailResponse dto = readUserService.getEmail(jwtToken);
        return responseHandler(HttpStatus.OK, "이메일 불러오기 성공", dto);
    }

    private CommonResponse<?> responseHandler(HttpStatus status, String msg, Object data){
        return CommonResponse.builder()
            .status(status)
            .msg(msg)
            .data(data)
            .build();
    }


}
