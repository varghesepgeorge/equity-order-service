package com.example.equityorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EquityOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquityOrderServiceApplication.class, args);
    }
}

@RestController
@RequestMapping("/orders")
class OrderController {

    private final Map<Long, String> orders = new HashMap<>();

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        if (!orders.containsKey(id)) {
            return new ResponseEntity<>("Order ID " + id + " does not exist.", HttpStatus.NOT_FOUND);
        }
        orders.remove(id);
        return new ResponseEntity<>("Order ID " + id + " deleted successfully.", HttpStatus.OK);
    }
}