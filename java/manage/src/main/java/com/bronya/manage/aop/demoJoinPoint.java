package com.bronya.manage.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // runtime annotation
@Target(ElementType.METHOD) // method annotation
public @interface demoJoinPoint {
}
