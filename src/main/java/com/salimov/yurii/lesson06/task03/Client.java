package com.salimov.yurii.lesson06.task03;

public final class Client {

    private String name;
    private String surname;
    private String phone;

    public Client(
            final String name,
            final String surname,
            final String phone
    ) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client:\n" +
                "\tname - " + this.name + "\n" +
                "\tsurname - " + this.surname + "\n" +
                "\tphone - " + this.phone;
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

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }
}
