package com.zhenhui.demo.tracer.udm.api.service;

import com.zhenhui.demo.tracer.udm.api.exception.BindingException;

public interface BindingService {

    boolean bindDevice(long userId, long deviceId) throws BindingException;

    boolean unbindDevice(long userId, long deviceId) throws BindingException;

    boolean isBound(long userId, long deviceId);
}

