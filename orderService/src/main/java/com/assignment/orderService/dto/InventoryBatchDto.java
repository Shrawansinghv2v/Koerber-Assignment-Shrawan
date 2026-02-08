package com.assignment.orderService.dto;

import lombok.Data;

@Data
public class InventoryBatchDto {

    private Long productId;
    private String productName;
    private int quantity;
}


