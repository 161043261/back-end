package com.bronya.projdemo.utils;

import java.util.Map;

public class ThreadLocalUtil { // thread-safe
    private static final ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static Map<String, Object> get() {
        return threadLocal.get(); // key = Thread.currentThread().getName()
    }

    public static void set(Map<String, Object> value) {
        threadLocal.set(value);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
