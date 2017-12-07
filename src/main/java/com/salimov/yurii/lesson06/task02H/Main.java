package com.salimov.yurii.lesson06.task02H;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 6.2 (Hibernate) Спроектировать базу «Квартиры». Каждая запись в базе содержит данные о
 * квартире (район, адрес, площадь, кол. комнат, цена). Сделать возможность
 * выборки квартир из списка по параметрам.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    private final static String PERSISTENCE_UNIT = "JPA_task62H";

    private static final Scanner scanner = new Scanner(System.in);
    private static DataBase dataBase;

    public static void main(String[] args) {
        try {
            dataBase = DataBase.getInstance();
            dataBase.connect(PERSISTENCE_UNIT);
            menu();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dataBase.disconnect();
            scanner.close();
        }
    }

    private static void menu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n-+-Menu-+-");
            System.out.println("1: add apartment");
            System.out.println("2: add random apartment");
            System.out.println("3: delete apartment");
            System.out.println("4: change apartment");
            System.out.println("5: view apartments");
            System.out.println("6: selection of apartments");
            System.out.println("0: exit");
            System.out.println("-+-+-+-+-+-");
            System.out.print("-> ");

            final String menuNumber = scanner.nextLine();
            switch (menuNumber) {
                case "1":
                    addApartment();
                    break;
                case "2":
                    insertRandomApartment();
                    break;
                case "3":
                    deleteApartment();
                    break;
                case "4":
                    changeApartment();
                    break;
                case "5":
                    viewApartments();
                    break;
                case "6":
                    selectionOfApartments();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.err.println("Unknown command! Try again...");
                    break;
            }
        }
    }

    private static void addApartment() {
        final Apartment apartment = newApartment();
        dataBase.addApartment(apartment);
    }

    private static void deleteApartment() {
        System.out.print("Enter apartment id: ");
        final long id = scanner.nextLong();
        dataBase.deleteApartment(id);
    }

    private static void changeApartment() {
        System.out.print("Enter apartment id: ");
        final long id = scanner.nextLong();
        final Apartment apartment = newApartment();
        dataBase.changeApartment(id, apartment);
    }

    private static void insertRandomApartment() {
        System.out.print("Enter apartments count: ");
        final int count = scanner.nextInt();
        final String[] addresses = {"5, Murmanska st.",
                "4, prosp. Glushkova",
                "55, Lomonosova st."};
        final Random random = new Random();
        String address;
        double price;
        Apartment apartment;
        for (int i = 0; i < count; i++) {
            address = addresses[random.nextInt(addresses.length)];
            price = random.nextDouble() * 100000;
            apartment = new Apartment(address, price);
            dataBase.addApartment(apartment);
        }
    }

    private static void viewApartments() {
        final Collection<Apartment> list = dataBase.getApartments();
        final String title = "All apartments:";
        printList(title, list);
    }

    private static void selectionOfApartments() {
        System.out.println("1: selection by region");
        System.out.println("2: selection by price");
        System.out.println("3: selection by area");
        System.out.println("4: selection by number of room");
        System.out.print("-> ");

        final String menuNumber = scanner.nextLine();
        switch (menuNumber) {
            case "1":
                selectionByRegion();
                break;
            case "2":
                selectionByPrice();
                break;
            case "3":
                selectionByArea();
                break;
            case "4":
                selectionByRoom();
                break;
            default:
                System.err.println("Unknown command! Try again...");
                break;
        }
    }

    private static void selectionByRegion() {
        System.out.print("Enter district:");
        String region = scanner.nextLine();
        final Collection<Apartment> list = dataBase.selectionByRegion(region);
        final String title = "All apartments in the region \"" + region + "\":";
        printList(title, list);
    }

    private static void selectionByPrice() {
        System.out.print("Enter price min:");
        final double priceMin = scanner.nextDouble();
        System.out.print("Enter price max:");
        final double priceMax = scanner.nextDouble();
        final Collection<Apartment> list = dataBase.selectionByPrice(priceMin, priceMax);
        final String title = "All apartments are priced from " + priceMin + " to " + priceMax + ":";
        printList(title, list);
    }

    private static void selectionByArea() {
        System.out.print("Enter the minimum area:");
        final double areaMin = scanner.nextDouble();
        System.out.print("Enter the maximum area:");
        final double areaMax = scanner.nextDouble();
        final Collection<Apartment> list = dataBase.selectionByArea(areaMin, areaMax);
        final String title = "All apartments with area from " + areaMin + " to " + areaMax + ":";
        printList(title, list);
    }

    private static void selectionByRoom() {
        System.out.print("Enter the minimum number of rooms: ");
        final int numberMin = scanner.nextInt();
        System.out.print("Enter the maximum number of rooms: ");
        final int numberMax = scanner.nextInt();
        final Collection<Apartment> list = dataBase.selectionByRoom(numberMin, numberMax);
        final String title = "All apartments with number of rooms from " + numberMin + " to " + numberMax + ":";
        printList(title, list);
    }

    private static Apartment newApartment() {
        System.out.print("Enter region: ");
        final String region = scanner.nextLine();
        System.out.print("Enter address: ");
        final String address = scanner.nextLine();
        System.out.print("Enter area: ");
        final double area = scanner.nextDouble();
        System.out.print("Enter number of rooms: ");
        final int number = scanner.nextInt();
        System.out.print("Enter price: ");
        final double price = scanner.nextDouble();
        return new Apartment(region, address, area, number, price);
    }

    private static void printList(final String title, final Collection<Apartment> apartments) {
        System.out.println(title);
        for (Apartment apartment : apartments) {
            System.out.println(apartment);
        }
    }
}
