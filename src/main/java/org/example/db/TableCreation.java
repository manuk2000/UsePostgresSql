package org.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TableCreation {
    public static void create() {
        Connection connection = DBConnector.getConnection();

        // Create tables
        createBookTable(connection);
        createCustomerTable(connection);
        createSaleTable(connection);

    }

    private static void createCustomerTable(Connection connection) {
        String createCustomerTableQuery = "CREATE TABLE IF NOT EXISTS Customers (" +
                "CustomerID SERIAL PRIMARY KEY," +
                "Name VARCHAR(255) NOT NULL," +
                "Email VARCHAR(255) NOT NULL," +
                "Phone VARCHAR(20) NOT NULL" +
                ")";
        executeCreateTableQuery(connection, createCustomerTableQuery);
    }

    private static void executeCreateTableQuery(Connection connection, String createTableQuery) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createBookTable(Connection connection) {
        String createBookTableQuery = "CREATE TABLE IF NOT EXISTS Books (" +
                "BookID SERIAL PRIMARY KEY," +
                "Title VARCHAR(255) NOT NULL," +
                "Author VARCHAR(255) NOT NULL," +
                "Genre VARCHAR(255) NOT NULL," +
                "Price DOUBLE PRECISION NOT NULL," +  // Change to DOUBLE PRECISION
                "QuantityInStock INT NOT NULL" +
                ")";
        executeCreateTableQuery(connection, createBookTableQuery);
    }

    private static void createSaleTable(Connection connection) {
        String createSaleTableQuery = "CREATE TABLE IF NOT EXISTS Sales (" +
                "SaleID SERIAL PRIMARY KEY," +
                "BookID INT REFERENCES Books(BookID)," +
                "CustomerID INT REFERENCES Customers(CustomerID)," +
                "DateOfSale VARCHAR(20) NOT NULL," +
                "QuantitySold INT NOT NULL," +
                "TotalPrice DOUBLE PRECISION NOT NULL" +  // Change to DOUBLE PRECISION
                ")";
        executeCreateTableQuery(connection, createSaleTableQuery);
    }

}

