package com.assignment.orderService.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private String productName;
    private int quantity;

        // no-arg constructor
        public OrderRequest() {
        }

        // all-args constructor
        public OrderRequest(long id, String name, int quantity) {
            this.productId = id;
            this.productName = name;
            this.quantity = quantity;
        }

        // getters and setters
        public long getId() { return productId; }
        public void setId(long id) { this.productId = id; }
        public String getName() { return productName; }
        public void setName(String name) { this.productName = name; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }


}
