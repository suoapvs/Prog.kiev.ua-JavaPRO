package com.salimov.yurii.lesson01.task01;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1.1 Создать аннотацию, которая принимает параметры для метода.
 * Написать код, который вызовет метод, помеченный этой аннотацией,
 * и передаст параметры аннотации в вызываемый метод.
 *
 * (a)Test(a = 2, b = 5)
 * public void test(int a, int b) {...}
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        callAnnotationMethods();
    }

    private static void callAnnotationMethods() {
        try {
            final Class<?> cls = MyClass.class;
            final Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    final Test test = method.getAnnotation(Test.class);
                    method.invoke(null, test.a(), test.b());

                }
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
}
