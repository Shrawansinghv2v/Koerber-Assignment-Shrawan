package com.assignment.Inventory.controller;

import com.assignment.Inventory.dto.UpdateInventoryRequest;
import com.assignment.Inventory.entity.InventoryBatch;
import com.assignment.Inventory.service.InventoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @GetMapping("/{productId}")
    public ResponseEntity<List<InventoryBatch>> getInventory(@PathVariable Long productId) {

        List<InventoryBatch> inventory = service.getInventory(productId);

        if (inventory.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventory);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> update(@Valid @RequestBody UpdateInventoryRequest request) {
        service.updateStock(request.getProductId(), request.getQuantity());
        return ResponseEntity.ok().build();
    }
}

