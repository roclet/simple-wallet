package com.wallet.security.repository;

import com.wallet.security.model.AccountUser;
import com.wallet.security.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory,Long> {
    Optional<List<TransactionHistory>> findByuserId(String userId);
}
