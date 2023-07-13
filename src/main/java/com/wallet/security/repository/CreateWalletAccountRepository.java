package com.wallet.security.repository;


import com.wallet.security.model.CreateWalletAccount;
import com.wallet.security.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreateWalletAccountRepository extends MongoRepository<CreateWalletAccount,String> {
    @Query("{userId:'?0'}")
    Optional<CreateWalletAccount> findCreateWalletAccountByUserId(String userId);
//    @Query("{userId:'?0'}")
//    List<TransactionHistory> findTransactionHistoriesByUserId(String userId);
}
