package com.wallet.security.repository;

import com.wallet.security.model.AccountUser;
import com.wallet.security.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
//import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionHistoryRepository extends MongoRepository<TransactionHistory,Long> {
    //System.out.println("Getting items for the category " + userId);
    @Query("{userId:'?0'}")
    List<TransactionHistory> findTransactionHistoriesByUserId(String userId);
}
