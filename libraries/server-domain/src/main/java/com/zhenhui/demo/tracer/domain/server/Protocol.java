package com.zhenhui.demo.tracer.domain.server;


import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.enums.CommandType;

import java.util.Set;
import java.util.concurrent.ExecutionException;

public interface Protocol {

    String getName();

    Set<CommandType> supportedCommands();

    void sendCommand(Connection connection, Command command) throws ExecutionException;

    void syncSendCommand(Connection connection, Command command) throws InterruptedException, ExecutionException;
}
