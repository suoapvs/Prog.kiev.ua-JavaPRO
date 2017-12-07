package com.salimov.yurii.lesson01.task03;

/**
 * 1.3 Написать код, который сериализирует и десериализирует в/из
 * файла все значения полей класса, которые отмечены аннотацией @Save.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        try {
            final String path = "c:/file.txt";
            final MyClass first = new MyClass("Hello World!", 1, 3.1415, 2L, true);
            System.out.println("Before serialization: " + first);
            serialize(first, path);

            final MyClass second = (MyClass) deserialize(MyClass.class, path);
            System.out.println("After deserialization: " + second);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void serialize(final Object obj, final String path) throws Exception {
        final Serialize serialize = new ReflectionSerialize();
        serialize.serialize(path, obj);
    }

    private static Object deserialize(final Class cls, final String path) throws Exception {
        final Serialize serialize = new ReflectionSerialize();
        return serialize.deserialize(path, cls);
    }
}
