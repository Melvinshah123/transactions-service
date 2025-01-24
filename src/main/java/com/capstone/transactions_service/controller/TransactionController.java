package com.capstone.transactions_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.transactions_service.entity.TransactionEntity;
import com.capstone.transactions_service.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;
    
    @GetMapping(" ")
    public ResponseEntity<List<TransactionEntity>> getAllTransactions(){
        return new ResponseEntity<>(transactionService.getAllTransactions(),HttpStatus.OK);
    }

    @GetMapping("/{tid}")
    public ResponseEntity<TransactionEntity> getATransaction(@PathVariable("tid") int transactionId){
        return new ResponseEntity<TransactionEntity>(transactionService.getAtransaction(transactionId),HttpStatus.OK);
    }

    @PostMapping(" ")
    public ResponseEntity<TransactionEntity> addTransaction(@RequestBody TransactionEntity newTransaction){
        return new ResponseEntity<>(transactionService.addTransaction(newTransaction),HttpStatus.OK);
    }

    @PutMapping(" ")
    public ResponseEntity<TransactionEntity>updateTransaction(TransactionEntity editedTransaction){
        return new ResponseEntity<>(transactionService.updateTransaction(editedTransaction),HttpStatus.OK);
    }

    @DeleteMapping("/{tid}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable("tid")int transactionId){
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
