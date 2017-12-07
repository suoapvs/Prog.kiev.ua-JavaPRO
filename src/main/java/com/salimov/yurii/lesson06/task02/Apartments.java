package com.salimov.yurii.lesson06.task02;

import java.sql.SQLException;

public interface Apartments {

    void addApartment(
            final String region,
            final String address,
            final double area,
            final int number,
            final double price

    ) throws SQLException;

    void insert(
            final String region,
            final String address,
            final double area,
            final int number,
            final double price
    ) throws SQLException;

    void insertRandomApartment() throws SQLException;

    void deleteApartment(final int id) throws SQLException;

    void changeApartmentPrice(final int id, final double price) throws SQLException;

    void viewApartments() throws SQLException;

    void viewApartments(final String str) throws SQLException;

    void selectionByRegion(final String region) throws SQLException;

    void selectionByPrice(final double priceMin, final double priceMax) throws SQLException;

    void selectionByArea(final double areaMin, final double areaMax) throws SQLException;

    void selectionByRoom(final double numberMin, final double numberMax) throws SQLException;
}
