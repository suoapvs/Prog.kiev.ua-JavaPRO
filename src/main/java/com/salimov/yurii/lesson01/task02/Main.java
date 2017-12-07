package com.salimov.yurii.lesson01.task02;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 1.2 Написать класс TextContainer, который содержит в себе строку.
 * С помощью механизма аннотаций указать
 * 1) в какой файл должен сохраниться текст;
 * 2) метод, который выполнит сохранение.
 * Написать класс Saver, который сохранит поле класса TextContainer
 * в указанный файл.
 *
 * (a)SaveTo(path = "c:\\file.txt")
 * class Container {
 *
 * String text = "...";
 *
 * (a)Saver
 * public void save(...) {
 * // code
 * }
 * }
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        final String text = "1.2 Написать класс TextContainer, который содержит в себе строку.";
        reflectionSave(text);
    }

    private static void reflectionSave(final String text) {
        try {
            final TextContainer container = new TextContainer(text);
            final Class<?> cls = container.getClass();
            final SaveTo SaveToAnnotation = cls.getAnnotation(SaveTo.class);
            final Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Saver.class)) {
                    method.invoke(container, SaveToAnnotation.path());
                }
            }
        } catch (IllegalAccessException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
}
