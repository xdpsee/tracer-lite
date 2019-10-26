package com.zhenhui.demo.tracer.server.mobile.codec;


import com.zhenhui.demo.tracer.domain.Command;
import com.zhenhui.demo.tracer.domain.enums.CommandType;
import com.zhenhui.demo.tracer.server.support.codec.AbstractProtocolEncoder;

import java.io.UnsupportedEncodingException;

public class MobileProtocolEncoder extends AbstractProtocolEncoder {

    @Override
    public byte[] encode(Command command) throws UnsupportedEncodingException {
        if (command.getType().equals(CommandType.TYPE_CUSTOM)) {
            String data = command.getAttributes().getString(Command.Attributes.KEY_DATA);
            return (data + "\r\n").getBytes("UTF-8");
        }

        return new byte[0];
    }

}


