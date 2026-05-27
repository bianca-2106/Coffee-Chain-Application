package com.coffeechain;

import com.coffeechain.controllers.LoginRequest;
import com.coffeechain.models.User;
import com.coffeechain.repositories.UserRepository;
import com.coffeechain.utils.DatabaseManager;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting Coffee Chain Application...");

        DatabaseManager.initializeDatabase();
        UserRepository userRepository = new UserRepository();

        Javalin app = Javalin.create(config ->
                config.bundledPlugins.enableCors(cors -> cors.addRule(it -> it.anyHost()))
        ).start(7070);

        System.out.println("API Server is running at http://localhost:7070");

        app.post("/api/register", ctx -> {
            User newUser = ctx.bodyAsClass(User.class);

            boolean success = userRepository.insertUser(newUser);

            if (!success) {
                ctx.status(400).result("Registration failed. Username might already exist.");
            } else {
                ctx.status(201).result("User registered successfully");
                System.out.println("New user registered in DB: " + newUser.getUsername());
            }
        });

        app.post("/api/login", ctx -> {
            LoginRequest loginRequest = ctx.bodyAsClass(LoginRequest.class);

            User foundUser = userRepository.findByUsername(loginRequest.getUsername());

            if (foundUser == null || !foundUser.getPassword().equals(loginRequest.getPassword())) {
                ctx.status(401).result("Invalid username or password");
            } else {
                ctx.status(200).json(foundUser);
                System.out.println("User logged in: " + foundUser.getUsername());
            }
        });
    }
}