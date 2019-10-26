package com.zhenhui.demo.tracer.domain.server;

import com.zhenhui.demo.tracer.domain.DeviceID;

public interface Message {

    DeviceID deviceId();

    byte[] rawBytes();

}



