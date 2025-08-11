package com.example.fraudchecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fraudchecker.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
