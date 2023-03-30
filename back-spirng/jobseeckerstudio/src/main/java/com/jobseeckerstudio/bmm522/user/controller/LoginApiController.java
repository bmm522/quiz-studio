package com.jobseeckerstudio.bmm522.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1")
@Controller
public class LoginApiController {

    @GetMapping("/login/{social}")
    public String moveSocialLoginForm(@PathVariable("social")String social){
        return "redirect:/oauth2/authorization/"+social;
    }

//    @GetMapping("email")
//    public String getEmail()
}
