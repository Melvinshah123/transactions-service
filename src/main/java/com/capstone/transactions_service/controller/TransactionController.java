package com.capstone.transactions_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.transactions_service.pojo.TransactionGetByCommAndEmailPojo;
import com.capstone.transactions_service.pojo.TransactionInputPojo;
import com.capstone.transactions_service.pojo.TransactionOutputPojo;
import com.capstone.transactions_service.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping(" ")
    public ResponseEntity<List<TransactionOutputPojo>> getAllTransactions() {
        return new ResponseEntity<List<TransactionOutputPojo>>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionOutputPojo> getATransaction(@PathVariable int transactionId) {
        return new ResponseEntity<TransactionOutputPojo>(transactionService.getAtransaction(transactionId),
                HttpStatus.OK);
    }

    @GetMapping("/communities/{communityId}")
    public ResponseEntity<List<TransactionOutputPojo>> getAllTransactionsFromCommunityId(
            @PathVariable int communityId) {
        return new ResponseEntity<List<TransactionOutputPojo>>(
                transactionService.getAllTransactionsFromCommunityId(communityId), HttpStatus.OK);
    }

    @GetMapping("/users/{email}")
    public ResponseEntity<List<TransactionOutputPojo>> getAllTransactionsFromEmail(@PathVariable String email) {
        return new ResponseEntity<List<TransactionOutputPojo>>(
                transactionService.getAllTransactionsFromEmail(email), HttpStatus.OK);
    }

    @GetMapping("/communities/users")
    public ResponseEntity<List<TransactionOutputPojo>> getAllTransactionsFromEmailAndCommunityId(
            @RequestBody TransactionGetByCommAndEmailPojo inputPojo) {
        return new ResponseEntity<List<TransactionOutputPojo>>(
                transactionService.getAllTransactionsFromEmailAndCommunityId(inputPojo.getEmail(),
                        inputPojo.getCommunityId()),
                HttpStatus.OK);
    }

    @PostMapping(" ")
    public ResponseEntity<TransactionOutputPojo> addTransaction(@RequestBody TransactionInputPojo newTransaction) {
        return new ResponseEntity<>(transactionService.addTransaction(newTransaction), HttpStatus.OK);
    }
}
