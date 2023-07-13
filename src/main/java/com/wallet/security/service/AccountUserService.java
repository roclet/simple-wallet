package com.wallet.security.service;


import com.wallet.security.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountUserService {
    CreateWalletAccount createAccountUser(CreateWalletAccount createWalletAccount);
    TransactionHistory creditAccountUser(CreditAccountRequest creditAccountRequest);
    TransactionHistory debitAccountUser(DebitAccountRequest debitAccountRequest);
    List<TransactionHistory> getTransactionHistoryByUserId(String userId);
    List<AccountUserResponse> getAllAccountUser();
    Optional<CreateWalletAccount> getWalletAccountByUserId(String userId);
}
