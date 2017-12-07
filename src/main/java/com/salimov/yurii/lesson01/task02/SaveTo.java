package com.salimov.yurii.lesson01.task02;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface SaveTo {

    String path();
}
