package org.example.repo;

import org.example.db.DBConnector;
import org.example.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepo {
    private Connection connection;

    public BookRepo() {
        this.connection = DBConnector.getConnection();
    }

    public void addBook(Book book) {
        String query = "INSERT INTO Books (Title, Author, Genre, Price, QuantityInStock) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setInt(5, book.getQuantityInStock());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM Books";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setBookID(resultSet.getInt("BookID"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setGenre(resultSet.getString("Genre"));
                book.setPrice(resultSet.getDouble("Price"));
                book.setQuantityInStock(resultSet.getInt("QuantityInStock"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Implement update and delete methods as needed
}
