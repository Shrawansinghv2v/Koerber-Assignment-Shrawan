package com.assignment.Inventory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateInventoryRequest {

    @NotNull
    private Long productId;

    @Min(1)
    private Integer quantity;
}


