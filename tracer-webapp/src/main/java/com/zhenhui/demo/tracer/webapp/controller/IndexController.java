package com.zhenhui.demo.tracer.webapp.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @PreAuthorize("hasAnyRole('USER','ADMIN', 'SUPER')")
    @GetMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }

}
