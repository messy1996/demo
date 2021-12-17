package com.example.demo.annotation;

import com.example.demo.enums.FieldType;

import java.lang.annotation.*;

/**
 * @author yqlin
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TreeField {
    /**
     * 所属类型
     * 三种: ID,PID,CHILD
     *
     * @return
     */
    FieldType fieldType();
}
