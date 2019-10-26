package com.zhenhui.demo.tracer.server.support.protocol;

import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.enums.CommandType;
import com.zhenhui.demo.tracer.domain.server.Connection;
import com.zhenhui.demo.tracer.domain.server.Protocol;
import io.netty.handler.codec.string.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractProtocol implements Protocol {

    private static final Logger logger = LoggerFactory.getLogger(AbstractProtocol.class);

    private final String name;

    protected AbstractProtocol(String name) {
        this.name = name;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public Set<CommandType> supportedCommands() {
        Set<CommandType> commands = new HashSet<>();

        commands.add(CommandType.TYPE_CUSTOM);

        return commands;
    }

    @Override
    public void sendCommand(Connection connection, Command command) {

        final Set<CommandType> supportedCommands = connection.protocol().supportedCommands();
        if (supportedCommands.contains(command.getType())) {
            connection.write(command);
        } else if (command.getType().equals(CommandType.TYPE_CUSTOM)) {
            try {
                String data = command.getAttributes().getString(Command.Attributes.KEY_DATA);
                if (connection.channel().pipeline().get(StringEncoder.class) != null) {
                    connection.write(data);
                } else {
                    connection.write(command);
                }
            } catch (Exception e) {
                logger.error("sendCommand exception", e);
            }
        } else {
            throw new IllegalArgumentException("Command " + command.getType() + " is not supported in protocol " + getName());
        }
    }

}
