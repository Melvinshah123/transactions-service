package com.capstone.transactions_service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstone.transactions_service.entity.TransactionEntity;
import com.capstone.transactions_service.pojo.TransactionInputPojo;
import com.capstone.transactions_service.pojo.TransactionOutputPojo;
import com.capstone.transactions_service.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    TransactionOutputPojo convertPojoToEntity(TransactionEntity entity) {
        TransactionOutputPojo pojo = new TransactionOutputPojo();
        BeanUtils.copyProperties(entity, pojo);
        return pojo;
    }

    List<TransactionOutputPojo> convertPojosToEntitys(List<TransactionEntity> entities) {
        List<TransactionOutputPojo> pojos = new ArrayList<>();
        for (TransactionEntity entity : entities) {
            pojos.add(convertPojoToEntity(entity));
        }
        return pojos;
    }

    public List<TransactionOutputPojo> getAllTransactions() {
        return convertPojosToEntitys(transactionRepository.findAll());
    }

    public List<TransactionOutputPojo> getAllTransactionsFromEmail(String email) {
        return convertPojosToEntitys(transactionRepository.findByEmail(email));
    }

    public List<TransactionOutputPojo> getAllTransactionsFromCommunityId(int communityId) {
        return convertPojosToEntitys(transactionRepository.findByCommunityId(communityId));
    }

    public List<TransactionOutputPojo> getAllTransactionsFromEmailAndCommunityId(String email, int communityId) {
        return convertPojosToEntitys(transactionRepository.findByEmailAndCommunityId(email, communityId));
    }

    public TransactionOutputPojo getAtransaction(int transactionId) {
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).orElse(null);
        return convertPojoToEntity(transactionEntity);
    }

    public TransactionOutputPojo addTransaction(TransactionInputPojo newTransaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        BeanUtils.copyProperties(newTransaction, transactionEntity);
        transactionEntity.setTransactionDateTime(LocalDateTime.now());
        return convertPojoToEntity(transactionRepository.saveAndFlush(transactionEntity));
    }

}
