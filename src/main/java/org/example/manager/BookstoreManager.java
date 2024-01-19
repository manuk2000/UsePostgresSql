package org.example.manager;

import org.example.dto.BookSaleInfo;
import org.example.model.Book;
import org.example.model.Customer;
import org.example.model.Sale;

import java.util.List;
import java.util.Map;

public interface BookstoreManager {

    // Join Query: Retrieve a list of all books sold, including the book title, customer name, and date of sale.
    List<BookSaleInfo> getBooksSoldInfo();

    // Aggregation Query: Find the total revenue generated from each genre of books.
    Map<String, Double> getTotalRevenueByGenre();

    void addBook(Book book);

    void addCustomer(Customer customer);

    void addSale(Sale sale);
}
