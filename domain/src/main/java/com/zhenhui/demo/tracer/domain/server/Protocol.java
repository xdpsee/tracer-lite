package com.zhenhui.demo.tracer.domain.server;


import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.enums.CommandType;

import java.util.Set;

public interface Protocol {

    String getName();

    Set<CommandType> supportedCommands();

    void sendCommand(Connection connection, Command command);

}
