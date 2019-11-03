package com.zhenhui.demo.tracer.control;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public interface ControlService {
    /**
     *
     * @param command command
     * @param timeout timeout seconds
     * @throws InterruptedException
     * @throws TimeoutException
     * @throws ExecutionException
     */
    void executeCommand(Command command, long timeout) throws InterruptedException, TimeoutException, ExecutionException;

}
