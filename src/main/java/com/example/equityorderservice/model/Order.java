package com.example.equityorderservice.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equity;
    private int quantity;
    private String orderType;
    private double price;

    // Default Constructor
    public Order() {
    }

    // Parameterized Constructor
    public Order(String equity, int quantity, String orderType, double price) {
        this.equity = equity;
        this.quantity = quantity;
        this.orderType = orderType;
        this.price = price;
    }

    // Getters and Setters

    // ID
    public Long getId() {
        return id;
    }

    // Equity
    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    // Quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Order Type
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    // Price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Override equals and hashCode (Optional but recommended)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    // toString method (Optional)
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", equity='" + equity + '\'' +
                ", quantity=" + quantity +
                ", orderType='" + orderType + '\'' +
                ", price=" + price +
                '}';
    }
}
