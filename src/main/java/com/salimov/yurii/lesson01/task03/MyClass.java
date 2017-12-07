package com.salimov.yurii.lesson01.task03;

import java.io.Serializable;

public final class MyClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @Save
    private String a;
    @Save
    private int b;
    @Save
    private double c;
    @Save
    private long d;

    private boolean e;

    public MyClass() {
    }

    public MyClass(
            final String a,
            final int b,
            final double c,
            final long d,
            final boolean e
    ) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
    }

    @Override
    public String toString() {
        return "a = " + this.a + ", b = " + this.b +
                ", c = " + this.c + ", d = " + this.d +
                ", e = " + this.e;
    }

    public String getA() {
        return this.a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public double getC() {
        return this.c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public long getD() {
        return this.d;
    }

    public void setD(long d) {
        this.d = d;
    }

    public boolean getE() {
        return this.e;
    }

    public void setE(boolean e) {
        this.e = e;
    }
}
