package com.coffeechain;

import com.coffeechain.utils.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Coffee Chain Application...");
        DatabaseManager.initializeDatabase();
        Server.startServer();
    }
}