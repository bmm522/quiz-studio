package com.jobseeckerstudio.bmm522.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
@Controller
public class LoginApiController {

    @GetMapping("/login/{social}")
    public String moveSocialLoginForm(@PathVariable("social")String social){
        return "redirect:/oauth2/authorization/"+social;
    }



    @GetMapping("/email")
    public void getEmail(@RequestHeader("Authorization")String authorization, @RequestHeader("RefreshToken")String refreshToken) {
        System.out.println(authorization);
        System.out.println(refreshToken);
    }
}
