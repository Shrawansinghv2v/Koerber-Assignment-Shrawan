package com.assignment.orderService.controller;

import com.assignment.orderService.dto.OrderRequest;
import com.assignment.orderService.entity.OrderEntity;
import com.assignment.orderService.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderEntity> place(@RequestBody OrderRequest req) {
        return ResponseEntity.ok(service.placeOrder(req));
    }
}

