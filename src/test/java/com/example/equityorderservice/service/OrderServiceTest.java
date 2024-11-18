package com.example.equityorderservice.service;

import com.example.equityorderservice.model.Order;
import com.example.equityorderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testSaveOrder() {
        Order order = new Order("MSFT", 200, "BUY", 300.00);

        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);

        assertEquals("MSFT", savedOrder.getEquity());
        assertEquals(200, savedOrder.getQuantity());
        assertEquals("BUY", savedOrder.getOrderType());
        assertEquals(300.00, savedOrder.getPrice());
    }
    @Test
    void testUpdateOrder() {
        Long orderId = 1L;
        Order existingOrder = new Order("AAPL", 100, "BUY", 150.00);
        //existingOrder.setId(orderId);

        Order updatedOrderDetails = new Order("AAPL", 150, "SELL", 155.00);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(updatedOrderDetails);

        Order updatedOrder = orderService.updateOrder(orderId, updatedOrderDetails);

        assertEquals(150, updatedOrder.getQuantity());
        assertEquals("SELL", updatedOrder.getOrderType());
        assertEquals(155.00, updatedOrder.getPrice());
    }

    @Test
    void testGetAllOrders() {
        List<Order> orders = Arrays.asList(
                new Order("AAPL", 100, "BUY", 150.00),
                new Order("GOOGL", 50, "SELL", 2800.00)
        );

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        assertEquals(2, result.size());
    }

    // Additional tests for updateOrder and getAllOrders...
}
