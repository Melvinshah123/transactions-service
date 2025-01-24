package com.capstone.transactions_service.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.transactions_service.entity.TransactionEntity;
import com.capstone.transactions_service.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<TransactionEntity> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public TransactionEntity getAtransaction(int transactionId){
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).orElse(null);
        return transactionEntity;
    }

    public TransactionEntity addTransaction(TransactionEntity newTransaction){
        return transactionRepository.saveAndFlush(newTransaction);
    }

    public TransactionEntity updateTransaction(TransactionEntity editedTransaction){
        return transactionRepository.save(editedTransaction);
    }

    public void deleteTransaction(int transactionId){
        transactionRepository.deleteById(transactionId);
    }

}
