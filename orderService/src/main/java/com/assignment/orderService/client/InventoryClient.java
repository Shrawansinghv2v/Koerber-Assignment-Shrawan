package com.assignment.orderService.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public void updateInventory(Long productId, int quantity) {

        UpdateInventoryRequest req =
                new UpdateInventoryRequest(productId, quantity);

        restTemplate.postForEntity(
                "http://localhost:8080/inventory/update",
                req,
                Void.class
        );
    }

    // simple DTO
    public record UpdateInventoryRequest(Long productId, int quantity) {}
}

