package com.wallet.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreditAccountRequest {
    private String userId;
    private String accountNumber;
    private BigDecimal amount;
    private String transactionDateTime;
}
