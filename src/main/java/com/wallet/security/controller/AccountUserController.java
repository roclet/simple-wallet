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

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
public class AccountUserController {

    private final AccountUserService accountUserService;

    @PostMapping({"/create/account"})
    @ResponseStatus(HttpStatus.CREATED)
    public void addAccountUser(@RequestBody CreateWalletAccount createWalletAccount){
        accountUserService.createAccountUser(createWalletAccount);
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

    @PostMapping("/credit/debit")
    public ResponseEntity<TransactionHistory> debitAccount(
            @RequestBody DebitAccountRequest request
    ) {
        return ResponseEntity.ok(accountUserService.debitAccountUser(request));
    }

    @GetMapping({"/get/user"})
    @ResponseStatus(HttpStatus.OK)
    public List<AccountUserResponse> getAllAccountUser(){
        return accountUserService.getAllAccountUser();
    }
}
