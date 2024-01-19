package org.example.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private double price;
    private int quantityInStock;

    public Book(final String title, final String author, final String genre, final double price, final int quantityInStock) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }
}