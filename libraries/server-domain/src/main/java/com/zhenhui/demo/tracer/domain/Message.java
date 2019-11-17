package com.zhenhui.demo.tracer.domain;


import com.zhenhui.demo.tracer.common.DeviceID;

public interface Message {

    DeviceID deviceId();

    byte[] rawBytes();

}



