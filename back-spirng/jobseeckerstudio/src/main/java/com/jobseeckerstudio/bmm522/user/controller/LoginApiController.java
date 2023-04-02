package com.jobseeckerstudio.bmm522.user.controller;

import com.jobseeckerstudio.bmm522.user.controller.dto.CommonResponse;
import com.jobseeckerstudio.bmm522.user.service.user.ReadUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@Controller
@RequiredArgsConstructor
public class LoginApiController {

    private final ReadUserService readUserService;

    @GetMapping("/login/{social}")
    public String moveSocialLoginForm(@PathVariable("social")String social){
        return "redirect:/oauth2/authorization/"+social;
    }



    @GetMapping("/email")
    public @ResponseBody CommonResponse<?> getEmail(@RequestHeader("Authorization")String authorization, @RequestHeader("RefreshToken")String refreshToken) {
         readUserService.getEmail(authorization, refreshToken);
    }
}
