package com.salimov.yurii.lesson07.task02;

import java.sql.*;

public final class TablesMySql {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/BankDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";

    private TablesMySql() {
    }

    public static void create() {
        try {
            final Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            final Statement statement = connection.createStatement();
            try {
                statement.execute("CREATE TABLE IF NOT EXISTS Clients(" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "name VARCHAR(10) NOT NULL," +
                        "surname VARCHAR(10) NOT NULL," +
                        "sex VARCHAR(6) DEFAULT NULL," +
                        "birth VARCHAR(20) DEFAULT NULL," +
                        "phone VARCHAR(13) NOT NULL," +
                        "email VARCHAR(30) DEFAULT NULL," +
                        "address_id INT Default NUll);");

                statement.execute("CREATE TABLE IF NOT EXISTS Profiles(" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "login VARCHAR(10) NOT NULL," +
                        "password VARCHAR(10) NOT NULL," +
                        "client_id INT NOT NULL);");

                statement.execute("CREATE TABLE IF NOT EXISTS Accounts(" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "number INT NOT NULL," +
                        "type VARCHAR(5) NOT NULL," +
                        "value DOUBLE(10,2) DEFAULT NULL," +
                        "profile_id INT NOT NULL);");

                statement.execute("CREATE TABLE IF NOT EXISTS Addresses(" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "country VARCHAR(50) NOT NULL," +
                        "city VARCHAR(50) NOT NULL," +
                        "street VARCHAR(50) NOT NULL," +
                        "house INT NOT NULL," +
                        "postcode INT DEFAULT NULL);");

                statement.execute("CREATE TABLE IF NOT EXISTS Rates(" +
                        "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                        "xmlID VARCHAR(10) NOT NULL," +
                        "name VARCHAR(10) DEFAULT NULL," +
                        "rate DOUBLE(6,2) NOT NULL," +
                        "date VARCHAR(10) DEFAULT NULL," +
                        "time VARCHAR(10) DEFAULT NULL," +
                        "ask DOUBLE(6,2) DEFAULT NULL," +
                        "bid DOUBLE(6,2) DEFAULT NULL);");
            } finally {
                connection.close();
                statement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
