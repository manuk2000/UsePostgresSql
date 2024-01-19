package org.example.db;

import org.example.manager.BookstoreManager;
import org.example.manager.managerIml.BookstoreManagerImpl;
import org.example.model.Book;
import org.example.model.Customer;
import org.example.model.Sale;

import java.util.Random;

public class DBInitialisation {

    public static void generateAndInsertThreeTimesBookAndCustomerAndSale() {
        // Create an instance of BookstoreManager
        BookstoreManager bookstoreManager = new BookstoreManagerImpl();

        // Add three books with random information
        for (int i = 0; i < 3; i++) {
            Book book = new Book(
                    "Book Title " + i,
                    "Author " + i,
                    "Genre " + i,
                    10 + Math.random() * 50,  // Random price between 10 and 60
                    (int) (Math.random() * 100)  // Random quantity between 0 and 100
            );
            bookstoreManager.addBook(book);
        }

        // Add three customers with random information
        for (int i = 0; i < 3; i++) {
            Customer customer = new Customer(
                    "Customer " + i,
                    "email" + i + "@example.com",
                    generateRandomPhoneNumber()
            );
            bookstoreManager.addCustomer(customer);
        }

        // Add three sales with random information
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            Sale sale = new Sale(
                    random.nextInt(3) + 1,  // Random BookID between 1 and 3
                    random.nextInt(3) + 1,  // Random CustomerID between 1 and 3
                    "2024-01-20",
                    random.nextInt(10) + 1,  // Random QuantitySold between 1 and 10
                    9.99 + random.nextDouble() * 50  // Random TotalPrice between 9.99 and 59.99
            );
            bookstoreManager.addSale(sale);
        }

        // Continue with other operations...
    }

    // Helper method to generate a random phone number
    private static String generateRandomPhoneNumber() {
        Random random = new Random();
        return String.format("%03d-%03d-%04d",
                random.nextInt(1000),
                random.nextInt(1000),
                random.nextInt(10000));
    }
}
