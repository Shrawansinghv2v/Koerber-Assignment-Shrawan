package com.assignment.orderService.service;

import com.assignment.orderService.client.UpdateInventory;
import com.assignment.orderService.client.UpdateInventory;
import com.assignment.orderService.dto.InventoryBatchDto;
import com.assignment.orderService.dto.OrderRequest;
import com.assignment.orderService.entity.OrderEntity;
import com.assignment.orderService.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final UpdateInventory updateInventory;

    @Transactional
    public OrderEntity placeOrder(OrderRequest request) {

        List<InventoryBatchDto> batches =
                updateInventory.getInventory(request.getProductId());

        if (batches == null || batches.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Product not found"
            );
        }

        int totalStock = batches.stream()
                .mapToInt(InventoryBatchDto::getQuantity)
                .sum();

        if (totalStock < request.getQuantity()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Insufficient stock"
            );
        }

        // deduct stock
        updateInventory.reduceStock(request.getProductId(), request.getQuantity());

        OrderEntity order = OrderEntity.builder()
                .productId(request.getProductId())
                .productName(batches.get(0).getProductName()) // or fetch separately
                .quantity(request.getQuantity())
                .status("PLACED")
                .orderDate(LocalDate.now())
                .build();

        return repository.save(order);
    }

}

