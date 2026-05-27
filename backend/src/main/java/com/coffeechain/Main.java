package com.coffeechain;

import com.coffeechain.controllers.LoginRequest;
import com.coffeechain.models.User;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class Main {
    //fake database for now
    private static Map<String, User> mockDatabase = new HashMap<>();

    public static void main(String[] args) {
        Javalin app = Javalin.create(config-> config.bundledPlugins.enableCors(cors-> cors.addRule(it-> it.anyHost()))).start(7070);

        System.out.println("API Server is running at http://localhost:7070");

        app.post("/api/register", ctx -> {
            User newUser = ctx.bodyAsClass(User.class);

            if(mockDatabase.containsKey(newUser.getUsername())){
                ctx.status(400).result("Username already exists");
            }
            else
            {
                mockDatabase.put(newUser.getUsername(), newUser);
                ctx.status(201).result("User registered successfully");
                System.out.println("New user registered: " + newUser.getUsername());
            }
        });

        app.post("/api/login", ctx -> {
            LoginRequest loginRequest = ctx.bodyAsClass(LoginRequest.class);

            User foundUser = mockDatabase.get(loginRequest.getUsername());

            if(foundUser == null || !foundUser.getPassword().equals(loginRequest.getPassword())) {
                ctx.status(401).result("Invalid username or password");
            }
            else
            {
                ctx.status(200).json(foundUser);
                System.out.println("User logged in "+foundUser.getUsername());
            }
        });
    }
}
