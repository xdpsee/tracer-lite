package com.zhenhui.demo.tracer.server.support.exception;

public interface DeviceException {

    class NotFound extends Exception {
        public NotFound() {
            super("Device not found!");
        }
    }

    class Expired extends Exception {
        public Expired() {
            super("Device expired!");
        }
    }
}

