package com.salimov.yurii.lesson07.task01;

import javax.persistence.*;

@Entity
@Table(name = "Dishes")
public final class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "discount", nullable = false)
    private double discount;

    @Column(name = "specification", nullable = false)
    private String specification;

    public Dish() {
    }

    public Dish(
            final String name,
            final double weight,
            final double price
    ) {
        this();
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public Dish(
            final String name,
            final double weight,
            final double price,
            final double discount
    ) {
        this(name, weight, price);
        setDiscount(discount);
    }

    public Dish(
            final String name,
            final double weight,
            final double price,
            final double discount,
            final String specification
    ) {
        this(name, weight, price, discount);
        this.specification = specification;
    }

    @Override
    public String toString() {
        return "Dish id = " + this.id + ":" +
                "\n\tname - " + this.name +
                "\n\tweight - " + this.weight +
                "\n\tprice - " + this.price +
                "\n\tdiscount - " + this.discount +
                "\n\tspecification - " + this.specification;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setWeight(final double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public void setDiscount(final double discount) {
        if (discount > 0 && discount < 100) {
            this.discount = discount;
        }
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setSpecification(final String specification) {
        this.specification = specification;
    }

    public String getSpecification() {
        return this.specification;
    }
}
