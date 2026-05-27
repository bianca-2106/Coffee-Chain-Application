package com.coffeechain.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String customerUsername;
    private Map<String, Integer> orderedItems;
    private String status;
    private String estimatedTime;
    private String rejectionReason;
}