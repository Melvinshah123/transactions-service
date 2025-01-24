package com.capstone.transactions_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.transactions_service.entity.TransactionEntity;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Integer>{

}
