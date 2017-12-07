package com.salimov.yurii.lesson01.task03;

public interface Serialize {

    void serialize(final String path, final Object obj) throws Exception;

    Object deserialize(final String path, final Class<?> cls) throws Exception;
}
