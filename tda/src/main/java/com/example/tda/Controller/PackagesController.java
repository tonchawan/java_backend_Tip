package com.example.tda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.tda.entity.Customer;
import com.example.tda.entity.Order;
import com.example.tda.entity.Packages;
import com.example.tda.repository.OrderRepository;
import com.example.tda.repository.PackagesRepository;

public class PackagesController {

    @Autowired
    private PackagesRepository packageRepository;
    
    @GetMapping("/packages")
    public ResponseEntity<List<Packages>> getPackages() {
        List<Packages> packages = packageRepository.findAll();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    @Autowired
    private OrderRepository orderRepository;
    
    @PostMapping("/buy")
    public ResponseEntity<String> order(@RequestBody Order order) {
        // Save the order to the database
        orderRepository.save(order);
        
        // Return a response with a success status code
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    
}
