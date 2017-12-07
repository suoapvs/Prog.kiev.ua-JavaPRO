package com.salimov.yurii.lesson02.task02;

import java.io.IOException;

public interface Reader <T> {

    T read() throws IOException;
}
