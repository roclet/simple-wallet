package com.wallet.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("wallet_account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateWalletAccount {
    @Id
    private String userId;
    private String accountNumber;
    private String accountTyper;
    private BigDecimal currentBalance;
    private String modifiedDateTime;
}
