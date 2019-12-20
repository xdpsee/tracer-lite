package com.zhenhui.demo.tracer.domain;


import com.zhenhui.demo.tracer.storage.api.domain.DeviceID;

public interface Message {

    DeviceID deviceId();

    byte[] rawBytes();

}



