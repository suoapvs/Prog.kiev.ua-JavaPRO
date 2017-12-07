package com.salimov.yurii.lesson07.task02;

import javax.persistence.*;

@Entity
@Table(name = "Clients")
public final class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "birth", nullable = false)
    private String birth;

    @Column(name = "email", nullable = false)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(mappedBy = "client")
    private Profile profile;

    public Client() {
    }

    public Client(
            final String name,
            final String surname,
            final String sex,
            final String birth,
            final String email,
            final String phone
    ) {
        this(name, surname, birth);
        this.sex = sex;
        this.email = email;
        this.phone = phone;
    }

    public Client(
            final String name,
            final String surname,
            final String phone
    ) {
        this();
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Client id = " + this.id + ":" +
                "\n\tname - " + this.name +
                "\n\tsurname - " + this.surname +
                "\n\tsex - " + this.sex +
                "\n\tbirth - " + this.birth +
                "\n\tphone - " + this.phone +
                "\n\temail - " + this.email +
                "\n\tprofile id = " + this.profile.getId();
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

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSex(final String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return this.sex;
    }

    public void setBirth(final String birth) {
        this.birth = birth;
    }

    public String getBirth() {
        return this.birth;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setAddress(final Address address) {
        this.address = address;
        if (address.getClient() != this) {
            address.setClient(this);
        }
    }

    public Address getAddress() {
        return this.address;
    }

    public void setProfile(final Profile profile) {
        this.profile = profile;
        if (profile.getClient() != this) {
            profile.setClient(this);
        }
    }

    public Profile getProfile() {
        return this.profile;
    }
}
