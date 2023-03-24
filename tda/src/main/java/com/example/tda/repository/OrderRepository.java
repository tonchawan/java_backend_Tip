package com.example.tda.repository;

import com.example.tda.entity.Order;
import com.example.tda.entity.Packages;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {
   
    @Query(value = "SELECT * FROM orders WHERE agent_id=?1 ORDER BY id", nativeQuery = true)
    public ArrayList<Order> findByAgentId(Integer id);

    @Query(value = (
        "SELECT * FROM orders JOIN packages ON orders.package_id=packages.id WHERE orders.agent_id =?1 ORDER BY orders.id ASC;")
        , nativeQuery = true)
    public ArrayList<Order> findReportByAgentId(Integer id);

}

