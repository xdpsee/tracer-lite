package com.zhenhui.demo.tracer.server.control.mq;

import com.zhenhui.demo.tracer.domain.Command;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommandResult implements Serializable {

    private static final long serialVersionUID = -1397274062686924595L;

    private Command command;

    private boolean success;

    private String message;
}

