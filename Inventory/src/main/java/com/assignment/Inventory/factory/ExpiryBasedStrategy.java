package com.assignment.Inventory.factory;

import com.assignment.Inventory.entity.InventoryBatch;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class ExpiryBasedStrategy implements InventoryStrategy {

    @Override
    public List<InventoryBatch> sortBatches(List<InventoryBatch> batches) {
        return batches.stream()
                .sorted(Comparator.comparing(InventoryBatch::getExpiryDate))
                .toList();
    }
}

