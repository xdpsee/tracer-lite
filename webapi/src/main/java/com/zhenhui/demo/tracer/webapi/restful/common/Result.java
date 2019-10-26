package com.zhenhui.demo.tracer.webapi.restful.common;

import com.zhenhui.demo.tracer.webapi.utils.Timestamp;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Result<T> {

    private Integer status;

    private String message;

    private String timestamp = Timestamp.now();

    private T data;

}
