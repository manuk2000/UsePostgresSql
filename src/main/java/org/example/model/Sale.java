package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    private int saleID;
    private int bookID;
    private int customerID;
    private String dateOfSale;
    private int quantitySold;
    private double totalPrice;

    public Sale(final int bookID, final int customerID, final String dateOfSale, final int quantitySold, final double totalPrice) {
        this.bookID = bookID;
        this.customerID = customerID;
        this.dateOfSale = dateOfSale;
        this.quantitySold = quantitySold;
        this.totalPrice = totalPrice;
    }
}