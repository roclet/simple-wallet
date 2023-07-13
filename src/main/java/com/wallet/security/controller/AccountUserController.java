package com.wallet.security.controller;


import com.wallet.security.exception.ApiRequestException;
import com.wallet.security.model.*;
import com.wallet.security.service.AccountUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/wallet/auth")
@RequiredArgsConstructor
public class AccountUserController {

    private final AccountUserService accountUserService;

    @PostMapping({"/create/account"})
    public ResponseEntity<CreateWalletAccount> addAccountUser(@RequestBody CreateWalletAccount createWalletAccount){
        System.out.println("xxxxxxxxxxxx"+createWalletAccount);
        final CreateWalletAccount response = accountUserService.createAccountUser(createWalletAccount);
        return  ResponseEntity.ok().body(response);
    }

    @GetMapping({"/transaction/history"})
    public ResponseEntity<List<TransactionHistory>> getTransactionHistoryByUserId(@RequestParam(value = "userId", required = true) final String userId) {
        try {
            List<TransactionHistory> transactionHistories = accountUserService.getTransactionHistoryByUserId(userId);
            final List<TransactionHistory> response = transactionHistories;
            return  ResponseEntity.ok().body(response);
        }catch (Exception e){
            throw  new ApiRequestException("Oop Cannot get transaction details Link to account");
        }
    }

    @PostMapping("/credit/account")
    public ResponseEntity<TransactionHistory> creditAccount(
            @RequestBody CreditAccountRequest request
    ) {
        return ResponseEntity.ok(accountUserService.creditAccountUser(request));
    }

    @PostMapping("/debit/account")
    public ResponseEntity<TransactionHistory> debitAccount(
            @RequestBody DebitAccountRequest request
    ) {
        return ResponseEntity.ok(accountUserService.debitAccountUser(request));
    }
    @GetMapping({"/get/user"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AccountUserResponse>> getAllAccountUser(){
        return ResponseEntity.ok(accountUserService.getAllAccountUser());
    }
    @GetMapping({"/get/wallet/account"})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<CreateWalletAccount>> getWalletAccountUser(@RequestParam(value = "userId", required = true) final String userId){
        Optional<CreateWalletAccount> response = accountUserService.getWalletAccountByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
