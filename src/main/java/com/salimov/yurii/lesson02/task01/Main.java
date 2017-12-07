package com.salimov.yurii.lesson02.task01;

/**
 * 2.1 Есть список поездов, представленный с виде XML.
 * Вывести на экран информацию о тех поездах, которые
 * отправляются сегодня с 15:00 до 19:00.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    public static void main(String[] args) {
        final String path = "src/main/java/com/salimov/yurii/lesson02/task01/Trains.xml";
        final Catalog catalog = Parser.read(path);
        final Monitor monitor = new Monitor(catalog);
        monitor.start();
        final Catalog catalog1 = monitor.getCatalog();
        Parser.write(path, catalog1);
    }
}
