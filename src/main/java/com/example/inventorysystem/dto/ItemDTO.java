
package com.example.inventorysystem.dto;

public record ItemDTO(
        Long id,
        String name,
        int quantity,
        double price
) {}
