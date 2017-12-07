package com.salimov.yurii.lesson07.task02;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Profiles")
public final class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private Collection<Account> accounts = new ArrayList<>();

    public Profile() {
    }

    public Profile(final String login, final String password) {
        this();
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Profile id = ").append(this.id)
                .append("\n\tlogin - ").append(this.login)
                .append("\n\tpassword - ").append(this.password)
                .append("\n\tclient id = ").append(this.client.getId())
        .append("\n\taccounts id:");
        for (Account account : this.accounts) {
            sb.append("\n\t\t").append(account.getId());
        }
        return sb.toString();
    }

    public void addAccount(final Account account) {
        this.accounts.add(account);
        if (account.getProfile() != this) {
            account.setProfile(this);
        }
    }

    public void addAccounts(final Collection<Account> accounts) {
        for (Account account : accounts) {
            addAccount(account);
        }
    }

    public void deleteAccount(Account account) {
        if (this.accounts.contains(account)) {
            this.accounts.remove(account);
        }
    }

    public void setId(final long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public String getLogin() {
        return this.login;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setClient(final Client client) {
        this.client = client;
        if (client.getProfile() != this) {
            client.setProfile(this);
        }
    }

    public Client getClient() {
        return this.client;
    }

    public void setAccounts(final Collection<Account> accounts) {
        this.accounts.clear();
        addAccounts(accounts);
    }

    public Collection<Account> getAccounts() {
        return this.accounts;
    }
}
