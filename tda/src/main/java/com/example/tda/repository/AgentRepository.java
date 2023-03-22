package com.example.tda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.tda.entity.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
    @Query(
    value = "SELECT * FROM agent WHERE agent.username = ?1", 
    nativeQuery = true)

    Agent findByUsername(String username);
}