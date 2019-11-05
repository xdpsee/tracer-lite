package com.zhenhui.demo.tracer.webapi.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Timestamp {

    private static final ThreadLocal<DateFormat> DATETIME_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssZ"));

    public static String now() {
        return now(DATETIME_FORMATTER.get());
    }

    public static String now(DateFormat formatter) {
        return formatter.format(new Date());
    }
}
