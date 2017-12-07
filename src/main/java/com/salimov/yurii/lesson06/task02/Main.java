package com.salimov.yurii.lesson06.task02;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

/**
 * 6.2 Спроектировать базу «Квартиры». Каждая запись в базе содержит данные о
 * квартире (район, адрес, площадь, кол. комнат, цена). Сделать возможность
 * выборки квартир из списка по параметрам.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ApartmentsDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";

    private static Apartments apartments;

    public static void main(String[] args) {
        try {
            try (Scanner sc = new Scanner(System.in)) {
                final Connection connection = getConnection();
                apartments = new ApartmentDB(connection);
                while (true) {
                    System.out.println("1: add apartment");
                    System.out.println("2: add random apartment");
                    System.out.println("3: delete apartment");
                    System.out.println("4: change apartment");
                    System.out.println("5: view apartments");
                    System.out.println("6: selection of apartments");
                    System.out.println("0: exit");
                    System.out.print("-> ");
                    final String number = sc.nextLine();
                    switch (number) {
                        case "1":
                            addApartment(sc);
                            break;
                        case "2":
                            insertRandomApartment();
                            break;
                        case "3":
                            deleteApartment(sc);
                            break;
                        case "4":
                            changeApartment(sc);
                            break;
                        case "5":
                            viewApartments();
                            break;
                        case "6":
                            selectionOfApartments(sc);
                            break;
                        case "0":
                            return;
                        default:
                            System.err.println("Unknown command! Try again...");
                            break;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static void addApartment(Scanner sc) throws SQLException {
        System.out.print("Enter region: ");
        final String region = sc.nextLine();
        System.out.print("Enter address: ");
        final String address = sc.nextLine();
        System.out.print("Enter area: ");
        final double area = sc.nextDouble();
        System.out.print("Enter number of rooms: ");
        final int number = sc.nextInt();
        System.out.print("Enter price: ");
        final double price = sc.nextDouble();
        apartments.insert(region, address, area, number, price);
    }

    private static void insertRandomApartment() throws SQLException {
        apartments.insertRandomApartment();
    }

    private static void deleteApartment(final Scanner sc) throws SQLException {
        System.out.print("Enter apartment id: ");
        final int id = sc.nextInt();
        apartments.deleteApartment(id);
    }

    private static void changeApartment(final Scanner sc) throws SQLException {
        System.out.print("Enter apartment id: ");
        final int id = sc.nextInt();
        System.out.print("Enter new price: ");
        final double price = sc.nextDouble();
        apartments.changeApartmentPrice(id, price);
    }

    private static void viewApartments() throws SQLException {
        apartments.viewApartments();
    }

    private static void selectionOfApartments(final Scanner sc) throws SQLException {
        System.out.println("1: selection by region");
        System.out.println("2: selection by price");
        System.out.println("3: selection by area");
        System.out.println("4: selection by number of room");
        System.out.print("-> ");
        final String number = sc.nextLine();
        switch (number) {
            case "1":
                selectionByRegion(sc);
                break;
            case "2":
                selectionByPrice(sc);
                break;
            case "3":
                selectionByArea(sc);
                break;
            case "4":
                selectionByRoom(sc);
                break;
        }
    }

    private static void selectionByRegion(final Scanner sc) throws SQLException {
        System.out.println("Enter district:");
        final String region = sc.nextLine();
        apartments.selectionByRegion(region);
    }

    private static void selectionByPrice(final Scanner sc) throws SQLException {
        System.out.println("Enter price min:");
        final double priceMin = sc.nextDouble();
        System.out.println("Enter price max:");
        final double priceMax = sc.nextDouble();
        apartments.selectionByPrice(priceMin, priceMax);
    }

    private static void selectionByArea(final Scanner sc) throws SQLException {
        System.out.println("Enter the minimum area:");
        final double areaMin = sc.nextDouble();
        System.out.println("Enter the maximum area:");
        final double areaMax = sc.nextDouble();
        apartments.selectionByArea(areaMin, areaMax);
    }

    private static void selectionByRoom(final Scanner sc) throws SQLException {
        System.out.print("Enter the minimum number of rooms: ");
        final double numberMin = sc.nextDouble();
        System.out.print("Enter the maximum number of rooms: ");
        final double numberMax = sc.nextDouble();
        apartments.selectionByRoom(numberMin, numberMax);
    }
}
