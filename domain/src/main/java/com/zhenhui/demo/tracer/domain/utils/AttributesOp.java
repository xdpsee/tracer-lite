package com.zhenhui.demo.tracer.domain.utils;

import java.util.HashMap;

@SuppressWarnings("unused")
public abstract class AttributesOp extends HashMap<String, Object> {

    public final String getString(String key) {

        if (null == key) {
            throw new IllegalArgumentException("key is null!");
        }

        Object v = get(key);
        if (v != null && !(v instanceof String)) {
            throw new IllegalArgumentException("value type is not string");
        }

        return (String) v;
    }

    public final Long getLong(String key) {

        if (null == key) {
            throw new IllegalArgumentException("key is null!");
        }

        Object v = get(key);
        if (v != null && !(v instanceof Long)) {
            throw new IllegalArgumentException("value type is not Long");
        }

        return (Long) v;
    }

    public final long getLong(String key, long defaultValue) {

        Long v = getLong(key);
        if (null == v) {
            return defaultValue;
        }

        return v;
    }

    public final Integer getInteger(String key) {

        if (null == key) {
            throw new IllegalArgumentException("key is null!");
        }

        Object v = get(key);
        if (v != null && !(v instanceof Integer)) {
            throw new IllegalArgumentException("value type is not Integer");
        }

        return (Integer) v;
    }

    public final int getInteger(String key, int defaultValue) {
        Integer v = getInteger(key);
        if (v != null) {
            return v;
        }

        return defaultValue;
    }

    public final Boolean getBoolean(String key) {
        if (null == key) {
            throw new IllegalArgumentException("key is null!");
        }

        Object v = get(key);
        if (v != null && !(v instanceof Long)) {
            throw new IllegalArgumentException("value type is not Boolean");
        }

        return (Boolean) v;
    }

    public final boolean getBoolean(String key, boolean defaultValue) {
        Boolean v = getBoolean(key);
        if (null == v) {
            return defaultValue;
        }

        return v;
    }

    public Double getDouble(String key) {

        if (null == key) {
            throw new IllegalArgumentException("key is null!");
        }

        Object v = get(key);

        if (v != null && !(v instanceof Double)) {
            throw new IllegalArgumentException(String.format("Key %s is not a Double value", key));
        }

        return (Double) v;
    }

    public Double getDouble(String key, double defaultValue) {
        Double v = getDouble(key);
        if (null == v) {
            return defaultValue;
        }

        return v;
    }
}
