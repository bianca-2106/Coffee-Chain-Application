package com.coffeechain;

import com.coffeechain.repositories.MenuItemRepository;
import io.javalin.Javalin;

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

        System.out.println("Web server is live! Listening on http://localhost:7070");
    }
}