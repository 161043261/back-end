package com.bronya.projdemo.utils;

public class ThreadLocalUtil { // thread-safe
    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static Object get() {
        return threadLocal.get(); // key = Thread.currentThread().getName()
    }

    public static void set(Object value) {
        threadLocal.set(value);
    }

    public static void remove() {
        threadLocal.remove();
    }
}
