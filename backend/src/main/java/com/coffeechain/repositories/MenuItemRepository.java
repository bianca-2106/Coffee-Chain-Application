package com.coffeechain.repositories;

import com.coffeechain.models.MenuItem;
import com.coffeechain.utils.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuItemRepository {

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menu = new ArrayList<>();
        String sql = "SELECT * FROM menu_items";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                MenuItem item = new MenuItem();
                item.setName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setAvailableQuantity(rs.getInt("availableQuantity"));
                item.setPicturePath(rs.getString("picturePath"));
                menu.add(item);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching menu: " + e.getMessage());
        }
        return menu;
    }

    public boolean addMenuItem(MenuItem item) {
        String sql = "INSERT INTO menu_items(name, price, availableQuantity, picturePath) VALUES(?,?,?,?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setDouble(2, item.getPrice());
            pstmt.setInt(3, item.getAvailableQuantity());
            pstmt.setString(4, item.getPicturePath());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Failed to insert menu item: " + e.getMessage());
            return false;
        }
    }
}