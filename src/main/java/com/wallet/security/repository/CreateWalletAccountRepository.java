package com.wallet.security.repository;


import com.wallet.security.model.CreateWalletAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreateWalletAccountRepository extends JpaRepository<CreateWalletAccount,String> {
    Optional<CreateWalletAccount> findByUserId(String userId);
    CreateWalletAccount findByAccountNumberEquals(String accountNumber);
}
