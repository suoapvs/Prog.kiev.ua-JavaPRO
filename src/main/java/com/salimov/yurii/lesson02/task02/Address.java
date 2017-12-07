package com.salimov.yurii.lesson02.task02;

public final class Address {

    private String country;
    private String city;
    private String street;

    @Override
    public String toString() {
        return "Address: " +
                "\n\tCountry: " + this.country +
                "\n\tCity: " + this.city +
                "\n\tStreet: " + this.street + "\n";
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
}
