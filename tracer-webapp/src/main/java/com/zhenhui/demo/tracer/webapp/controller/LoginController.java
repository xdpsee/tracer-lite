package com.zhenhui.demo.tracer.webapp.controller;

import com.zhenhui.demo.tracer.security.UserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal final UserPrincipal user) {

        if (user != null) {
            return "redirect:/";
        }

        return "login";
    }

}

