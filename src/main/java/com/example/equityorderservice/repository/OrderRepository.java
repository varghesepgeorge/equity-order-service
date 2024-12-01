package com.example.equityorderservice.repository;

import com.example.equityorderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteById(Long id);
}