package org.example;

import org.example.db.DBCreateAndInitialisationRandomInformation;
import org.example.dto.BookSaleInfo;
import org.example.manager.BookstoreManager;
import org.example.manager.managerIml.BookstoreManagerImpl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBCreateAndInitialisationRandomInformation.accept();
        BookstoreManager bookstoreManager = new BookstoreManagerImpl();

        Scanner scanner = new Scanner(System.in);

        // Example usage of the methods
        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Retrieve a list of all books sold");
            System.out.println("2. Find the total revenue generated from each genre of books");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    List<BookSaleInfo> booksSoldInfo = bookstoreManager.getBooksSoldInfo();
                    displayBooksSoldInfo(booksSoldInfo);
                    break;
                case 2:
                    Map<String, Double> totalRevenueByGenre = bookstoreManager.getTotalRevenueByGenre();
                    displayTotalRevenueByGenre(totalRevenueByGenre);
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void displayBooksSoldInfo(List<BookSaleInfo> booksSoldInfo) {
        System.out.println("Books Sold Information:");
        for (BookSaleInfo info : booksSoldInfo) {
            System.out.println("Book Title: " + info.getBookTitle());
            System.out.println("Customer Name: " + info.getCustomerName());
            System.out.println("Date of Sale: " + info.getDateOfSale());
            System.out.println("----------------------------");
        }
    }

    private static void displayTotalRevenueByGenre(Map<String, Double> totalRevenueByGenre) {
        System.out.println("Total Revenue by Genre:");
        for (Map.Entry<String, Double> entry : totalRevenueByGenre.entrySet()) {
            System.out.println("Genre: " + entry.getKey());
            System.out.println("Total Revenue: $" + entry.getValue());
            System.out.println("----------------------------");
        }
    }
}
