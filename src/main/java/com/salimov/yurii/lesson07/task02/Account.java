package com.salimov.yurii.lesson07.task02;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
public final class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "number", nullable = false)
    private long number;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    private double value;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Account() {
    }

    public Account(final long number, final String type) {
        this();
        this.number = number;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account id = " + this.id + ":" +
                "\n\tnumber - " + this.number +
                "\n\ttype - " + this.type +
                "\n\tvalue - " + this.value +
                "\n\tprofile id = " + this.profile.getId();
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setNumber(final long number) {
        this.number = number;
    }

    public long getNumber() {
        return this.number;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setProfile(final Profile profile) {
        this.profile = profile;
        if (!profile.getAccounts().contains(this)) {
            profile.addAccount(this);
        }
    }

    public Profile getProfile() {
        return this.profile;
    }
}
