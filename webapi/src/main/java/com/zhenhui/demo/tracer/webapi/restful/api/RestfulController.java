package com.zhenhui.demo.tracer.webapi.restful.api;

import com.zhenhui.demo.tracer.control.Command;
import com.zhenhui.demo.tracer.control.ControlService;
import com.zhenhui.demo.tracer.domain.enums.CommandType;
import com.zhenhui.demo.tracer.webapi.restful.exception.ErrorCodes;
import com.zhenhui.demo.tracer.webapi.restful.exception.ServiceException;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/api")
public class RestfulController {

    @SuppressWarnings("unused")
    @Reference(version = "1.0.0")
    private ControlService controlService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/test")
    public String test() {

        throw new ServiceException(ErrorCodes.DEVICE_NOT_FOUND);

    }

    @Async
    @GetMapping("/command")
    public String testCommand() {

        Command command = new Command();
        command.setType(CommandType.TYPE_CUSTOM.name());
        command.setUuid(UUID.randomUUID().toString());


        try {
            controlService.executeCommand(command, 3000);
        } catch (Exception e) {
            return e.getClass().getSimpleName();
        }

        return "OK";

    }

}

