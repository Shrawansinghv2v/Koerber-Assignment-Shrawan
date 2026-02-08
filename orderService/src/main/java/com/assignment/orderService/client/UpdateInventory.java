package com.assignment.orderService.client;

import com.assignment.orderService.dto.InventoryBatchDto;
import com.assignment.orderService.dto.UpdateInventoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UpdateInventory {

    private final RestTemplate restTemplate;

    public List<InventoryBatchDto> getInventory(Long productId) {

        ResponseEntity<List<InventoryBatchDto>> response =
                restTemplate.exchange(
                        "http://localhost:8080/inventory/" + productId,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<InventoryBatchDto>>() {}
                );

        return response.getBody();
    }

    public void reduceStock(Long productId, int qty) {
        UpdateInventoryRequest req = new UpdateInventoryRequest(productId, qty);

        restTemplate.postForEntity(
                "http://localhost:8080/inventory/update",
                req,
                Void.class
        );
    }
}

