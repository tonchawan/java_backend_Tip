package com.example.tda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tda.entity.Agent;

public interface AgentRepository extends JpaRepository<Agent, Integer> {
    Agent findByEmail(String email);
}