
package com.example.inventorysystem.dto;

import java.time.LocalDate;

public record TransactionDTO(
        Long id,
        String type,
        LocalDate date,
        int quantity,
        Long itemId,
        Long supplierId
) {}
