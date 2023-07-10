package com.wallet.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document("balance_account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountBalance {
    @Id
    private String accountNumner;
    private int balance;
    private String lastUpdate;
}
