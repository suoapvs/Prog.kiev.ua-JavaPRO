package com.salimov.yurii.lesson02.task01;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public final class Monitor {

    private final Scanner scan = new Scanner(System.in);

    private Catalog catalog;

    public Monitor(final Catalog catalog) {
        this.catalog = catalog;
    }

    public void start() {
        String info = "+++++++++\nMenu:\nAdd train - 1\nFilter train - 2\nShow trains - 3\nExit - 0\n+++++++++";
        boolean isExit = false;
        while (!isExit) {
            try {
                System.out.println(info);
                int answer = Integer.parseInt(read());
                switch (answer) {
                    case (1):
                        addTrain();
                        break;
                    case (2):
                        filterTrains();
                        break;
                    case (3):
                        showCatalog();
                        break;
                    case (0):
                        isExit = true;
                        break;
                    default:
                        System.out.println("Unknown command!");
                        break;
                }
            } catch (NumberFormatException ex) {
                System.err.println("Incorrect answer!");
            } catch (DateTimeParseException ex) {
                System.err.println("Incorrect date or Departure!");
            }
        }
    }

    private void addTrain() {
        System.out.print("id = ");
        final int id = Integer.parseInt(read());
        System.out.print("From - ");
        final String from = read();
        System.out.print("To - ");
        final String to = read();
        System.out.print("Date (dd.MM.yyyy) - ");
        final String date = read();
        System.out.print("Departure (HH:mm) - ");
        final String departure = read();
        final Train train = new Train(id, from, to, date, departure);
        catalog.add(train);
    }

    private void filterTrains() {
        System.out.print("Date (dd.MM.yyyy) - ");
        final String date2 = read();
        System.out.print("From time (HH:mm) - ");
        final String fromTime = read();
        System.out.print("To time (HH:mm) - ");
        final String toTime = read();
        this.catalog = this.catalog.filter(date2, fromTime, toTime);
        showCatalog();
    }

    private void showCatalog() {
        System.out.println("-----------");
        System.out.print(this.catalog);
        System.out.println("-----------");
    }

    public void setCatalog(final Catalog catalog) {
        this.catalog = catalog;
    }

    public Catalog getCatalog() {
        return this.catalog;
    }

    private String read() {
        return this.scan.nextLine();
    }
}
