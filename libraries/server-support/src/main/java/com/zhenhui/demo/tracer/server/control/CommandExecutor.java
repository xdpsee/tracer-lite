package com.zhenhui.demo.tracer.server.control;

import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.server.control.mq.CommandResult;
import com.zhenhui.demo.tracer.server.control.mq.MessageProducer;
import com.zhenhui.demo.tracer.server.support.server.LocalConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeoutException;

@Component
public class CommandExecutor {

    private static final String COMMAND_EXECUTE_REQUEST_DESTINATION = "pubsub.tracer.command.execute.request";

    private static final String COMMAND_EXECUTE_RESPONSE_DESTINATION = "pubsub.tracer.command.execute.response";

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private CoordinatorManager coordinatorManager;

    @Autowired
    private LocalConnectionManager localConnectionManager;

    // 执行并等待
    public void execute(Command command, long timeout) throws InterruptedException, JmsException, TimeoutException {

        messageProducer.send(COMMAND_EXECUTE_REQUEST_DESTINATION, command);
        coordinatorManager.await(command, timeout);
    }

    // 真正执行命令
    @JmsListener(destination = COMMAND_EXECUTE_REQUEST_DESTINATION)
    public final void handleExecuteRequestMessage(Command command) {

        Connection connection = localConnectionManager.getConnection(command.getDeviceId());
        if (null == connection) {
            return;
        }

        try {
            connection.syncSend(command);
        } catch (Exception e) {
            coordinatorManager.signal(command);
        }
    }

    // 监听执行结果
    @JmsListener(destination = COMMAND_EXECUTE_RESPONSE_DESTINATION)
    public final void handleExecuteResponseMessage(CommandResult result) {

        try {
            Command command = result.getCommand();
            coordinatorManager.signal(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

