package org.example.manager.managerIml;

import org.example.db.DBConnector;
import org.example.dto.BookSaleInfo;
import org.example.manager.BookstoreManager;
import org.example.model.Book;
import org.example.model.Customer;
import org.example.model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookstoreManagerImpl implements BookstoreManager {
    private final Connection connection;

    public BookstoreManagerImpl() {
        this.connection = DBConnector.getConnection();
    }

    @Override
    public List<BookSaleInfo> getBooksSoldInfo() {
        List<BookSaleInfo> bookSaleInfoList = new ArrayList<>();
        String query = "SELECT Books.Title AS BookTitle, Customers.Name AS CustomerName, Sales.DateOfSale " +
                "FROM Sales " +
                "JOIN Books ON Sales.BookID = Books.BookID " +
                "JOIN Customers ON Sales.CustomerID = Customers.CustomerID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                BookSaleInfo bookSaleInfo = new BookSaleInfo();
                bookSaleInfo.setBookTitle(resultSet.getString("BookTitle"));
                bookSaleInfo.setCustomerName(resultSet.getString("CustomerName"));
                bookSaleInfo.setDateOfSale(resultSet.getString("DateOfSale"));
                bookSaleInfoList.add(bookSaleInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookSaleInfoList;
    }

    @Override
    public Map<String, Double> getTotalRevenueByGenre() {
        Map<String, Double> totalRevenueByGenre = new HashMap<>();
        String query = "SELECT Genre, SUM(TotalPrice) AS TotalRevenue " +
                "FROM Books " +
                "JOIN Sales ON Books.BookID = Sales.BookID " +
                "GROUP BY Genre";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String genre = resultSet.getString("Genre");
                double totalRevenue = resultSet.getDouble("TotalRevenue");
                totalRevenueByGenre.put(genre, totalRevenue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return totalRevenueByGenre;
    }

    @Override
    public void addBook(Book book) {
        String query = "INSERT INTO Books (Title, Author, Genre, Price, QuantityInStock) VALUES (?, ?, ?, ?, ?)";
        executeInsertQuery(query, book.getTitle(), book.getAuthor(), book.getGenre(), book.getPrice(), book.getQuantityInStock());
    }

    @Override
    public void addCustomer(Customer customer) {
        String query = "INSERT INTO Customers (Name, Email, Phone) VALUES (?, ?, ?)";
        executeInsertQuery(query, customer.getName(), customer.getEmail(), customer.getPhone());
    }

    @Override
    public void addSale(Sale sale) {
        String query = "INSERT INTO Sales (BookID, CustomerID, DateOfSale, QuantitySold, TotalPrice) VALUES (?, ?, ?, ?, ?)";
        executeInsertQuery(query, sale.getBookID(), sale.getCustomerID(), sale.getDateOfSale(), sale.getQuantitySold(), sale.getTotalPrice());
    }

    private void executeInsertQuery(String query, Object... parameters) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
