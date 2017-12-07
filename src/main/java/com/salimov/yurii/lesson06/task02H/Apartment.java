package com.salimov.yurii.lesson06.task02H;

import javax.persistence.*;

@Entity
@Table(name = "Apartments")
public final class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String region;

    @Column(nullable = false)
    private String address;

    private double area;

    private int rooms;

    private double price;

    public Apartment() {
    }

    public Apartment(
            final String region,
            final String address,
            final double area,
            final int rooms,
            final double price
    ) {
        this(address, price);
        this.region = region;
        this.area = area;
        this.rooms = rooms;
    }

    public Apartment(final String address, final double price) {
        this();
        this.address = address;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartment id = " + this.id + ":" +
                "\n\tregion - " + this.region +
                "\n\taddress - " + this.address +
                "\n\tarea - " + this.area +
                "\n\trooms - " + this.rooms +
                "\n\tprice - " + this.price;
    }

    public void setRegion(final String region) {
        this.region = region;
    }

    public String getRegion() {
        return this.region;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setArea(final double area) {
        this.area = area;
    }

    public double getArea() {
        return this.area;
    }

    public void setRooms(final int rooms) {
        this.rooms = rooms;
    }

    public int getRooms() {
        return this.rooms;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }
}
