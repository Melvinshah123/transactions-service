package com.capstone.transactions_service.service;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.capstone.transactions_service.entity.TransactionEntity;
import com.capstone.transactions_service.pojo.CommunityMembershipPojo;
import com.capstone.transactions_service.pojo.CommunityPojo;
import com.capstone.transactions_service.pojo.CommunityUpdateAmountPojo;
import com.capstone.transactions_service.pojo.DateTimePojo;
import com.capstone.transactions_service.pojo.TransactionInputPojo;
import com.capstone.transactions_service.pojo.TransactionOutputPojo;
import com.capstone.transactions_service.pojo.UserOutputDataPojo;
import com.capstone.transactions_service.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    TransactionOutputPojo convertPojoToEntity(TransactionEntity entity) {
        TransactionOutputPojo pojo = new TransactionOutputPojo();
        BeanUtils.copyProperties(entity, pojo);
        RestClient restClient = RestClient.create();
        UserOutputDataPojo responseUser = restClient.get()
                .uri("http://localhost:5001/api/users/email/" + entity.getEmail())
                .retrieve().body(UserOutputDataPojo.class);
        pojo.setUser(responseUser);
        CommunityPojo responseCommunity = restClient.get()
                .uri("http://localhost:5002/api/communities/" + entity.getCommunityId())
                .retrieve().body(CommunityPojo.class);
        pojo.setCommunity(responseCommunity);
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

    @SuppressWarnings("null")
    public TransactionOutputPojo addTransaction(TransactionInputPojo newTransaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        double amount = newTransaction.getAmount();
        double interestAmount = newTransaction.getInterestAmount();
        double newAmount = amount + interestAmount;
        if (newTransaction.getTransactionType().equals("Debit")) {
            newAmount *= -1;
            amount *= -1;
        }
        CommunityUpdateAmountPojo requestData = new CommunityUpdateAmountPojo(newTransaction.getCommunityId(),
                newTransaction.getEmail(), newAmount);
        RestClient restClient = RestClient.create();
        restClient.put()
                .uri("http://localhost:5002/api/communities/amount")
                .body(requestData)
                .retrieve().body(CommunityPojo.class);
        requestData.setAmount(amount);
        restClient.put()
                .uri("http://localhost:5005/api/CommunityMembership/amount")
                .body(requestData)
                .retrieve().body(CommunityMembershipPojo.class);
        BeanUtils.copyProperties(newTransaction, transactionEntity);

        DateTimePojo dateTimePojo = restClient.put().uri("http://localhost:5010/api/time")
                .body(new DateTimePojo(0, LocalDateTime.now())).retrieve().body(DateTimePojo.class);
        transactionEntity.setTransactionDateTime(dateTimePojo.getDateTime());

        return convertPojoToEntity(transactionRepository.saveAndFlush(transactionEntity));
    }

    
    public List<TransactionEntity> getTransactionsByCommunityIdAndDateRange(
            int communityId,
            LocalDateTime startDate,
            LocalDateTime endDate) {
        
        return transactionRepository.findByCommunityIdAndTransactionTypeAndTransactionDateTimeBetween(communityId, "Credit",startDate, endDate);
    }

=======
public class TransactionService {

>>>>>>> parent of 97bf3ca (second commit)
}
