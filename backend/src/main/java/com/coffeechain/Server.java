package com.coffeechain;

import com.coffeechain.repositories.MenuItemRepository;
import io.javalin.Javalin;
import com.coffeechain.models.MenuItem;

public class Server {
    public static void startServer() {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(it -> it.anyHost());
            });
        }).start(7070);
        MenuItemRepository menuRepo = new MenuItemRepository();
        app.get("/", ctx -> {
            ctx.result("Welcome to the Coffee Chain API!");
        });
        app.get("/api/menu", ctx -> {
            ctx.json(menuRepo.getAllMenuItems());
        });
        app.post("/api/menu", ctx -> {
            MenuItem newItem = ctx.bodyAsClass(MenuItem.class);
            boolean success = menuRepo.addMenuItem(newItem);
            if (success) {
                ctx.status(201).result("Successfully added " + newItem.getName() + " to the menu!");
            } else {
                ctx.status(500).result("Server error: Could not save to database.");
            }
        });

        System.out.println("Web server is live! Listening on http://localhost:7070");
    }
}