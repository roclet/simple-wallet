package com.wallet.security.service.Impl;


import com.wallet.security.model.*;
import com.wallet.security.repository.CreateWalletAccountRepository;
import com.wallet.security.repository.TransactionHistoryRepository;
import com.wallet.security.repository.UserRepository;
import com.wallet.security.service.AccountUserService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountUserServiceImpl implements AccountUserService {

    private final UserRepository userRepository;
    private final CreateWalletAccountRepository createWalletAccountRepository;
    private final TransactionHistoryRepository transactionHistoryRepository;

    @Override
    public CreateWalletAccount createAccountUser(CreateWalletAccount createWalletAccount) {
        createWalletAccountRepository.save(createWalletAccount);
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .transactionAmount(createWalletAccount.getCurrentBalance())
                .userId(createWalletAccount.getUserId())
                .transactionType("credit")
                .accountNumber(createWalletAccount.getAccountNumber())
                .transactionDateTime(createWalletAccount.getModifiedDateTime())
                .build();
        final TransactionHistory response = transactionHistoryRepository.save(transactionHistory);
        System.out.println("TransactionHistory"+response);
        return createWalletAccountRepository.save(createWalletAccount);
    }

    @Override
    public TransactionHistory creditAccountUser(CreditAccountRequest creditAccountRequest) {
        CreateWalletAccount createOrupdateWalletAcccount = createWalletAccountRepository.findCreateWalletAccountByUserId(creditAccountRequest.getUserId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("cannot Find account by user Id %s",creditAccountRequest.getUserId() )
                ));
        BigDecimal amount = creditAccountRequest.getAmount();
        createOrupdateWalletAcccount.setCurrentBalance(createOrupdateWalletAcccount.getCurrentBalance().add(amount));
        createWalletAccountRepository.save(createOrupdateWalletAcccount);
        TransactionHistory transactionHistory = TransactionHistory.builder()
                .transactionAmount(creditAccountRequest.getAmount())
                .userId(creditAccountRequest.getUserId())
                .transactionType("credit")
                .accountNumber(creditAccountRequest.getAccountNumber())
                .transactionDateTime(creditAccountRequest.getTransactionDateTime())
                .build();
        TransactionHistory transactionHistory1 = transactionHistoryRepository.save(transactionHistory);
        return transactionHistory1;
    }

    @Override
    public TransactionHistory debitAccountUser(DebitAccountRequest debitAccountRequest) {
        System.out.println("createOrupdateWalletAcccount"+ debitAccountRequest );
        CreateWalletAccount createOrupdateWalletAcccount = createWalletAccountRepository.findCreateWalletAccountByUserId(debitAccountRequest.getUserId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("cannot Find account by user Id %s",debitAccountRequest.getUserId() )
                ));

        if(createOrupdateWalletAcccount.getCurrentBalance().compareTo(BigDecimal.ONE ) == 1
         && debitAccountRequest.getAmount().compareTo(BigDecimal.ONE) == 1){
            BigDecimal amount = debitAccountRequest.getAmount();
            createOrupdateWalletAcccount.setCurrentBalance(createOrupdateWalletAcccount.getCurrentBalance().subtract(amount));
            createWalletAccountRepository.save(createOrupdateWalletAcccount);
            TransactionHistory transactionHistory = TransactionHistory.builder()
                    .transactionAmount(debitAccountRequest.getAmount())
                    .userId(debitAccountRequest.getUserId())
                    .transactionType("debit")
                    .accountNumber(debitAccountRequest.getAccountNumber())
                    .transactionDateTime(debitAccountRequest.getTransactionDateTime())
                    .build();
            TransactionHistory transactionHistory1 = transactionHistoryRepository.save(transactionHistory);
            return transactionHistory1;
        }
        return null;
    }

    @Override
    public List<TransactionHistory> getTransactionHistoryByUserId(String userId) {
        //transactionHistoryRepository.findAll().
        System.out.println("Getting items for the category " + userId);
        List<TransactionHistory> transactionHistories = transactionHistoryRepository.findTransactionHistoriesByUserId(userId);
        return transactionHistories;
    }

    @Override
        public List<AccountUserResponse> getAllAccountUser(){
            List<AccountUser> accountUsers = userRepository.findAll();
            return accountUsers.stream().map(accountUser -> mapToAccountUserRespose(accountUser)).toList();
        }

    @Override
    public Optional<CreateWalletAccount> getWalletAccountByUserId(String userId) {
        CreateWalletAccount createOrupdateWalletAcccount = createWalletAccountRepository.findCreateWalletAccountByUserId(userId)
                .orElseThrow(() -> new RuntimeException(
                        String.format("cannot Find account by user Id %s",userId )
                ));
        return Optional.ofNullable(createOrupdateWalletAcccount);
    }

    private AccountUserResponse mapToAccountUserRespose(AccountUser accountUsers) {
            return  AccountUserResponse.builder()
                    .firstname(accountUsers.getFirstname())
                    .lastname(accountUsers.getLastname())
                    .email(accountUsers.getEmail())
                    .password(accountUsers.getPassword())
                    .build();
        }
}
