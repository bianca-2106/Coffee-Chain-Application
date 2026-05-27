package com.coffeechain.repositories;

import com.coffeechain.models.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    private static final String DB_URL = "jdbc:sqlite:coffeechain.db";

    public boolean insertUser(User user) {
        String sql = "INSERT INTO users(username, password, role, fullName, address, phoneNumber) VALUES(?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getRole());
            pstmt.setString(4, user.getFullName());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getPhoneNumber());

            pstmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.err.println("Failed to insert user (username likely already exists): " + e.getMessage());
            return false;
        }
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("fullName"),
                        rs.getString("address"),
                        rs.getString("phoneNumber")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding user in database: " + e.getMessage());
        }

        return null;
    }
}