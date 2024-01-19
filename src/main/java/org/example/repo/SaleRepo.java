package org.example.repo;

import org.example.db.DBConnector;
import org.example.model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleRepo {
    private Connection connection;

    public SaleRepo() {
        this.connection = DBConnector.getConnection();
    }

    public void addSale(Sale sale) {
        String query = "INSERT INTO Sales (BookID, CustomerID, DateOfSale, QuantitySold, TotalPrice) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, sale.getBookID());
            preparedStatement.setInt(2, sale.getCustomerID());
            preparedStatement.setString(3, sale.getDateOfSale());
            preparedStatement.setInt(4, sale.getQuantitySold());
            preparedStatement.setDouble(5, sale.getTotalPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM Sales";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Sale sale = new Sale();
                sale.setSaleID(resultSet.getInt("SaleID"));
                sale.setBookID(resultSet.getInt("BookID"));
                sale.setCustomerID(resultSet.getInt("CustomerID"));
                sale.setDateOfSale(resultSet.getString("DateOfSale"));
                sale.setQuantitySold(resultSet.getInt("QuantitySold"));
                sale.setTotalPrice(resultSet.getDouble("TotalPrice"));
                sales.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }

    // Implement update and delete methods as needed
}
