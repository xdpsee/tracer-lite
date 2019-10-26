package com.zhenhui.demo.tracer.server.support.exception;

public interface MessageException {

    class ExecuteFailure extends Exception {
        public ExecuteFailure(String message, Throwable cause) {
            super(message, cause);
        }
    }

    class DecodeFailure extends Exception {
        public DecodeFailure(String message) {
            super(message);
        }

        public DecodeFailure(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
