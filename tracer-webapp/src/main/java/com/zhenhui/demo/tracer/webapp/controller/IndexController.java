package com.zhenhui.demo.tracer.webapp.controller;

import com.zhenhui.demo.tracer.security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    //@Autowired
    //private BindingQueryService bindingQueryService;

    @PreAuthorize("hasAnyRole('USER','ADMIN', 'SUPER')")
    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserPrincipal user, Model model) {

        //List<Device> devices = bindingQueryService.queryUserDevices(user.getId());

        //model.addAttribute("devices", devices);

        return "index";
    }

}
