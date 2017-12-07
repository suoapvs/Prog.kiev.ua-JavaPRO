package com.salimov.yurii.lesson01.task01;

public final class MyClass {

    @Test(a = 2, b = 5)
    public static void sum(final int a, final int b) {
        System.out.println("a + b = " + (a + b));
    }

    public static void sub(final int a, final int b) {
        System.out.println("a - b = " + (a - b));
    }

    @Test(a = 2, b = 5)
    public static void mul(final int a, final int b) {
        System.out.println("a * b = " + (a * b));
    }

    public static  void div(final int a, final int b) {
        System.out.println("a / b = " + (a / b));
    }
}
