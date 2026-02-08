package com.assignment.Inventory.service;

import com.assignment.Inventory.entity.InventoryBatch;
import com.assignment.Inventory.factory.InventoryStrategyFactory;
import com.assignment.Inventory.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;
    private final InventoryStrategyFactory factory;

    public List<InventoryBatch> getInventory(Long productId) {
        List<InventoryBatch> batches = repository.findByProductIdOrderByExpiryDateAsc(productId);

        if (batches.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }

        return factory.getStrategy().sortBatches(batches);
    }

    @Transactional
    public void updateStock(Long productId, int qty) {

        List<InventoryBatch> batches = repository.findByProductIdOrderByExpiryDateAsc(productId);

        for (InventoryBatch batch : batches) {
            if (qty <= 0) break;

            int deduct = Math.min(batch.getQuantity(), qty);
            batch.setQuantity(batch.getQuantity() - deduct);
            qty -= deduct;
        }

        repository.saveAll(batches);
    }
}

