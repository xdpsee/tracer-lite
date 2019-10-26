package com.zhenhui.demo.tracer.webapi.restful.api;

import com.zhenhui.demo.tracer.webapi.restful.exception.ErrorCodes;
import com.zhenhui.demo.tracer.webapi.restful.exception.ServiceException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class RestfulController {


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/test")
    public String test() {

        throw new ServiceException(ErrorCodes.DEVICE_NOT_FOUND);

    }

}

