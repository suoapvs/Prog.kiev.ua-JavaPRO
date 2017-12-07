package com.salimov.yurii.lesson06.task03;

import java.sql.*;

public final class DataBase {

    private static DataBase dataBase;

    private Connection connection;

    private DataBase() {
    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public void connect(
            final String url,
            final String user,
            final String password
    ) throws SQLException {
        if (this.connection == null) {
            this.connection = DriverManager.getConnection(url, user, password);
            initialization();
        }
    }

    public void disconnect() throws SQLException {
        if (this.connection != null) {
            this.connection.close();
        }
    }

    public void initialization() throws SQLException {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Clients");
            st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "surname VARCHAR(20) DEFAULT NULL, " +
                    "phone VARCHAR(20) NOT NULL);");

            st.execute("DROP TABLE IF EXISTS Products");
            st.execute("CREATE TABLE Products (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "price DOUBLE(10,2) NOT NULL, " +
                    "information VARCHAR(128) DEFAULT NULL);");

            st.execute("DROP TABLE IF EXISTS Orders");
            st.execute("CREATE TABLE Orders (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "clientId INT NOT NULL, " +
                    "productId INT NOT NULL, " +
                    "comment VARCHAR(128) DEFAULT NULL);");
        }
    }

    public void insertClient(final Client client) throws SQLException {
        final String sql = "INSERT INTO Clients (name, surname, phone) VALUES(?, ?, ?);";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, client.getName());
            ps.setString(2, client.getSurname());
            ps.setString(3, client.getPhone());
            ps.executeUpdate();
        }
        System.out.println(client);
    }

    public void insertProduct(final Product product) throws SQLException {
        final String sql = "INSERT INTO Products (name, price, information) VALUES(?, ?, ?);";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getInformation());
            ps.executeUpdate();
        }
        System.out.println(product);
    }

    public void insertOrder(final Order order) throws SQLException {
        String sql = "INSERT INTO Orders (clientId, productId, comment) VALUES(?, ?, ?);";
        try (PreparedStatement ps = this.connection.prepareStatement(sql)) {
            ps.setInt(1, order.getClientId());
            ps.setInt(2, order.getProductId());
            ps.setString(3, order.getComment());
            ps.executeUpdate();
        }
    }

    public String getClientsTable() throws SQLException {
        return getTable("Clients");
    }

    public String getProductsTable() throws SQLException {
        return getTable("Products");
    }

    public String getOrdersTable() throws SQLException {
        return getTable("Orders");
    }

    private String getTable(String tableName) throws SQLException {
        final StringBuilder sb = new StringBuilder();
        final String sql = "SELECT * FROM " + tableName + ";";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData md = rs.getMetaData();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                sb.append(md.getColumnName(i)).append("\t\t");
            }
            sb.append("\n");
            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    sb.append(rs.getString(i)).append("\t\t");
                }
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
