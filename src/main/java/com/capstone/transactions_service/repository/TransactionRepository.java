package com.capstone.transactions_service.repository;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.transactions_service.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> findByEmail(String email);
=======
public class TransactionRepository {
>>>>>>> parent of 97bf3ca (second commit)

    List<TransactionEntity> findByCommunityId(int communityId);

    List<TransactionEntity> findByEmailAndCommunityId(String email, int communityId);

    // List<TransactionEntity> findByEmailStartingWith(String emailPrefix);
    List<TransactionEntity> findByCommunityIdAndTransactionTypeAndTransactionDateTimeBetween(
            int communityId, String transactionType,LocalDateTime startDateTime, LocalDateTime endDateTime);
}
