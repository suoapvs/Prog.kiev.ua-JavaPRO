package com.salimov.yurii.lesson06.task03;

public final class Product {

    private String name;
    private double price;
    private String information;

    public Product(final String name, final double price) {
        this.name = name;
        this.price = price;
    }

    public Product(
            final String name,
            final double price,
            final String information
    ) {
        this(name, price);
        this.information = information;
    }

    @Override
    public String toString() {
        return "Product:" + "\n" +
                "\tname - " + this.name + "\n" +
                "\tprice - " + this.price + "\n" +
                "\tinformation - " + this.information;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setInformation(final String information) {
        this.information = information;
    }

    public String getInformation() {
        return this.information;
    }
}
