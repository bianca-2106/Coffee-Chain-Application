package com.coffeechain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String password;
    private String role;

    private String fullName;
    private String address;
    private String phoneNumber;
}