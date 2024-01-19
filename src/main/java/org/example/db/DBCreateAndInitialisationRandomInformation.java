package org.example.db;

public class DBCreateAndInitialisationRandomInformation {
    public static void accept() {
        TableCreation.create();
        DBInitialisation.generateAndInsertThreeTimesBookAndCustomerAndSale();
    }
}
