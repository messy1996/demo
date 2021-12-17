package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * @author jay
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TreeEntity {
}