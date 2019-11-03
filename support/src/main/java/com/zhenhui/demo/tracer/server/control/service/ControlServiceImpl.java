package com.zhenhui.demo.tracer.server.control.service;

import com.zhenhui.demo.tracer.control.Command;
import com.zhenhui.demo.tracer.control.CommandExecutor;
import com.zhenhui.demo.tracer.control.ControlService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Service(version = "1.0.0")
public class ControlServiceImpl implements ControlService {

    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    public void executeCommand(Command command, long timeout) throws InterruptedException, TimeoutException, ExecutionException {

        try {
            commandExecutor.execute(command, timeout);
        } catch (Exception e) {
            if (e instanceof TimeoutException) {
                throw e;
            }

            if (e instanceof JmsException) {
                throw new ExecutionException(e);
            }

            if (e instanceof InterruptedException) {
                throw e;
            }

            throw new ExecutionException(e);
        }

    }
}
