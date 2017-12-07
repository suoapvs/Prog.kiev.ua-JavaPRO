package com.salimov.yurii.lesson07.task01;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * 7.1 Создать таблицу «Меню в ресторане». Колонки: название блюда, его стоимость,
 * вес, наличие скидки. Написать код для добавления записей в таблицу и их выборки
 * по критериям «стоимость от-до», «только со скидкой», выбрать набор блюд так,
 * чтобы их суммарный вес был не более 1 КГ.
 *
 * @author Yuriy Salimov (yuriy.alex.salimov@gmail.com)
 */
public class Main {

    private final static String PERSISTENCE_UNIT = "JPA_task71";
    private final static Scanner scanner = new Scanner(System.in);

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
        }
    }

    private static void menu() {
        while (true) {
            System.out.println("\n-+-Menu-+-");
            System.out.println("1: add dish");
            System.out.println("2: add random dish");
            System.out.println("3: delete dish");
            System.out.println("4: change dish");
            System.out.println("5: view dishes");
            System.out.println("6: selection of dishes");
            System.out.println("0: exit");
            System.out.println("-+-+-+-+-+-");
            System.out.print("-> ");
            String menuNumber = scanner.nextLine();
            switch (menuNumber) {
                case "1":
                    addDish();
                    break;
                case "2":
                    insertRandomDishes();
                    break;
                case "3":
                    deleteDish();
                    break;
                case "4":
                    changeDish();
                    break;
                case "5":
                    viewDishes();
                    break;
                case "6":
                    selectionOfDishes();
                    break;
                case "0":
                    return;
                default:
                    System.err.println("Unknown command! Try again...");
                    break;
            }
        }
    }

    private static void addDish() {
        final Dish dish = newDish();
        dataBase.addDish(dish);
    }

    private static void insertRandomDishes() {
        System.out.print("Enter dish count: ");
        final int count = scanner.nextInt();
        final Random random = new Random();
        for (int i = 0; i < count; i++) {
            final String name = "name " + i;
            final double price = random.nextDouble() * 300;
            final double weight = random.nextDouble() * 1000;
            final double discount = random.nextDouble() * 100;
            final Dish dish = new Dish(name, weight, price, discount);
            dataBase.addDish(dish);
        }
    }

    private static void deleteDish() {
        System.out.print("Enter dish id: ");
        final long id = scanner.nextLong();
        dataBase.deleteDish(id);
    }

    private static void changeDish() {
        System.out.print("Enter dish id: ");
        final long id = scanner.nextLong();
        final Dish dish = newDish();
        dish.setId(id);
        dataBase.changeDish(dish);
    }

    private static void viewDishes() {
        final Collection<Dish> list = dataBase.getDishes();
        final String title = "All dishes:";
        printList(title, list);
    }

    private static void selectionOfDishes() {
        System.out.println("1: selection by price");
        System.out.println("2: selection by weight");
        System.out.println("3: selection by discount");
        System.out.println("4: selection by all discount");
        System.out.print("-> ");
        final String menuNumber = scanner.nextLine();
        switch (menuNumber) {
            case "1":
                selectionByPrice();
                break;
            case "2":
                selectionByWeight();
                break;
            case "3":
                selectionByDiscount();
                break;
            case "4":
                selectionByAllDiscount();
                break;
            default:
                System.err.println("Unknown command! Try again...");
                break;
        }
    }

    private static void selectionByPrice() {
        System.out.print("Enter price min: ");
        final double priceMin = scanner.nextDouble();
        System.out.print("Enter price max: ");
        final double priceMax = scanner.nextDouble();
        final Collection<Dish> list = dataBase.selectionByPrice(priceMin, priceMax);
        final String title = "All dishes are priced from " + priceMin + " to " + priceMax + ":";
        printList(title, list);
    }

    private static void selectionByWeight() {
        System.out.print("Enter weight min: ");
        final double weightMin = scanner.nextDouble();
        System.out.print("Enter weight max: ");
        final double weightMax = scanner.nextDouble();
        final Collection<Dish> list = dataBase.selectionByWeight(weightMin, weightMax);
        final String title = "All dishes with a weight from " + weightMin + " to " + weightMax + ":";
        printList(title, list);
    }

    private static void selectionByDiscount() {
        System.out.print("Enter discount min: ");
        final double discountMin = scanner.nextDouble();
        System.out.print("Enter discount max: ");
        final double discountMax = scanner.nextDouble();
        final Collection<Dish> list = dataBase.selectionByDiscount(discountMin, discountMax);
        final String title = "All dishes with a discount from " + discountMin + " to " + discountMax + ":";
        printList(title, list);
    }

    private static void selectionByAllDiscount() {
        final Collection<Dish> list = dataBase.selectionByDiscount();
        final String title = "All dishes with a discount:";
        printList(title, list);
    }

    private static Dish newDish() {
        System.out.print("Enter name: ");
        final String name = scanner.nextLine();
        System.out.print("Enter weight: ");
        final double weight = scanner.nextDouble();
        System.out.print("Enter price: ");
        final double price = scanner.nextDouble();
        System.out.print("Enter discount: ");
        final double discount = scanner.nextDouble();
        System.out.print("Enter specification: ");
        final String specification = scanner.nextLine();
        return new Dish(name, weight, price, discount, specification);
    }

    private static void printList(final String title, final Collection<Dish> dishes) {
        System.out.println(title);
        for (Dish dish : dishes) {
            System.out.println(dish);
        }
    }
}
