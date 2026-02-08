package com.assignment.Inventory.factory;

import org.springframework.stereotype.Component;

@Component
public class InventoryStrategyFactory {

    private final ExpiryBasedStrategy expiryBasedStrategy;

    public InventoryStrategyFactory(ExpiryBasedStrategy expiryBasedStrategy) {
        this.expiryBasedStrategy = expiryBasedStrategy;
    }

    public InventoryStrategy getStrategy() {
        return expiryBasedStrategy;
    }
}

