package com.salimov.yurii.lesson02.task02;

import java.util.Arrays;

public final class JSON {

    private String name;
    private String surname;
    private String[] phones;
    private String[] sites;
    private Address address;

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" +
                "Surname: " + this.surname + "\n" +
                "Phones: " + Arrays.toString(this.phones) + "\n" +
                "Sites: " + Arrays.toString(this.sites) + "\n" +
                this.address + "\n";
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setPhones(final String[] phones) {
        this.phones = phones;
    }

    public String[] getPhones() {
        return this.phones;
    }

    public void setSites(final String[] sites) {
        this.sites = sites;
    }

    public String[] getSites() {
        return this.sites;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return this.address;
    }
}
