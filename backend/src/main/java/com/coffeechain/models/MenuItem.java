package com.coffeechain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    private String name;
    private double price;
    private int availableQuantity;
    private String picturePath;
}