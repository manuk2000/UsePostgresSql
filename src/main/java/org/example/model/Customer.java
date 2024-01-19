package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int customerID;
    private String name;
    private String email;
    private String phone;

    public Customer(final String name, final String email, final String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}