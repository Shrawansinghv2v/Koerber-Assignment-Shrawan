package com.assignment.orderService.service;

import com.assignment.orderService.client.UpdateInventory;
import com.assignment.orderService.dto.InventoryBatchDto;
import com.assignment.orderService.dto.OrderRequest;
import com.assignment.orderService.entity.OrderEntity;
import com.assignment.orderService.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @Mock
    private UpdateInventory updateInventory;

    @InjectMocks
    private OrderService service;

    @Test
    void shouldSaveOrderSuccessfully() {

        OrderRequest request = new OrderRequest(1001L, "Laptop", 5);

        InventoryBatchDto batch = new InventoryBatchDto();
        batch.setProductId(1001L);
        batch.setProductName("Laptop");
        batch.setQuantity(10);

        List<InventoryBatchDto> mockBatches = Arrays.asList(batch);

        OrderEntity saved = OrderEntity.builder()
                .orderId(1L)
                .productId(1001L)
                .productName("Laptop")
                .quantity(5)
                .status("PLACED")
                .build();

        when(updateInventory.getInventory(1001L)).thenReturn(mockBatches);
        when(repository.save(any(OrderEntity.class))).thenReturn(saved);
        doNothing().when(updateInventory).reduceStock(1001L, 5);

        OrderEntity result = service.placeOrder(request);

        assertNotNull(result);
        assertEquals("PLACED", result.getStatus());
        verify(repository, times(1)).save(any());
        verify(updateInventory, times(1)).getInventory(1001L);
        verify(updateInventory, times(1)).reduceStock(1001L, 5);
    }
}
