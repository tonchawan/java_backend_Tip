package com.example.tda.repository;

import com.example.tda.entity.Order;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
   
    @Query(value = "SELECT * FROM orders WHERE agent_id=?1", nativeQuery = true)
    public ArrayList<Order> findByAgentId(Integer id);
}

