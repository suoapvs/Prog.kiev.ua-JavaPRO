package com.salimov.yurii.lesson07.task02;

import javax.persistence.*;

@Entity
@Table(name = "Addresses")
public final class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne(mappedBy = "address")
    private Client client;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "house", nullable = false)
    private int house;

    @Column(name = "postcode", nullable = false)
    private String postcode;

    public Address() {
    }

    public Address(
            final String country,
            final String city,
            final String street,
            final int house,
            final String postcode
    ) {
        this();
        this.country = country;
        this.city = city;
        this.street = street;
        this.house = house;
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return "Address id = " + this.id + ":" +
                "\n\tclient - " + this.client.getName() +
                " " + this.client.getSurname() +
                "\n\t" + this.country + ", " + this.city +
                ", " + this.street + ", " + this.house +
                "\n\tpostcode = " + this.postcode;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setClient(final Client client) {
        this.client = client;
        if (client.getAddress() != this) {
            client.setAddress(this);
        }
    }

    public Client getClient() {
        return this.client;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getStreet() {
        return this.street;
    }

    public void setHouse(final int house) {
        this.house = house;
    }

    public int getHouse() {
        return this.house;
    }

    public void setPostcode(final String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return this.postcode;
    }
}
