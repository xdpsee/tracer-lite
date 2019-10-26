package com.zhenhui.demo.tracer.domain.server;

import com.zhenhui.demo.tracer.domain.Command;

public interface Encoder {

    byte[] encode(Command command) throws Exception;

}


