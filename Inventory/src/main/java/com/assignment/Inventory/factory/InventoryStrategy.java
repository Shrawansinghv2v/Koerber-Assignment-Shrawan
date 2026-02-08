package com.assignment.Inventory.factory;

import com.assignment.Inventory.entity.InventoryBatch;

import java.util.List;

public interface InventoryStrategy {
    List<InventoryBatch> sortBatches(List<InventoryBatch> batches);
}
