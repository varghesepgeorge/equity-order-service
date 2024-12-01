package com.example.equityorderservice.service;

import com.example.equityorderservice.model.Order;
import com.example.equityorderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // Save a new order
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    // Update an existing order
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setEquity(orderDetails.getEquity());
        order.setQuantity(orderDetails.getQuantity());
        order.setOrderType(orderDetails.getOrderType());
        order.setPrice(orderDetails.getPrice());
        return orderRepository.save(order);
    }

    // List orders based on important attributes
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Delete an existing order
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order with ID " + id + " does not exist.");
        }
        orderRepository.deleteById(id);
    }
}