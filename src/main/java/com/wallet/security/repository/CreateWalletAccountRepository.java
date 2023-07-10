package com.wallet.security.repository;


import com.wallet.security.model.CreateWalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreateWalletAccountRepository extends MongoRepository<CreateWalletAccount,String> {
    Optional<CreateWalletAccount> findCreateWalletAccountByUserId(String userId);
}
