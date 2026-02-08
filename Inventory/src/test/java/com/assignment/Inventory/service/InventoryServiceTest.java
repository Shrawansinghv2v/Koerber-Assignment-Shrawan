package com.assignment.Inventory.service;

import com.assignment.Inventory.entity.InventoryBatch;
import com.assignment.Inventory.factory.InventoryStrategyFactory;
import com.assignment.Inventory.factory.InventoryStrategy;
import com.assignment.Inventory.repository.InventoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    InventoryRepository repo;

    @Mock
    InventoryStrategyFactory factory;

    @Mock
    InventoryStrategy strategy;

    @InjectMocks
    InventoryService service;

    @Test
    void shouldReturnSortedInventory() {

        List<InventoryBatch> batches = List.of(
                new InventoryBatch(1L,100L,"Test",10, LocalDate.now().plusDays(5)),
                new InventoryBatch(2L,100L,"Test",10,LocalDate.now())
        );

        when(repo.findByProductIdOrderByExpiryDateAsc(100L)).thenReturn(batches);
        when(factory.getStrategy()).thenReturn(strategy);
        when(strategy.sortBatches(batches)).thenReturn(batches);

        List<InventoryBatch> result = service.getInventory(100L);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getBatchId());
    }
}

