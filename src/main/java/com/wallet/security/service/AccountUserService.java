package com.wallet.security.service;


import com.wallet.security.model.*;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountUserService {
    void createAccountUser(CreateWalletAccount createWalletAccount);
    TransactionHistory creditAccountUser(CreditAccountRequest creditAccountRequest);
    TransactionHistory debitAccountUser(DebitAccountRequest debitAccountRequest);
    List<TransactionHistory> getTransactionHistoryByUserId(String userId);
    List<AccountUserResponse> getAllAccountUser();
}
