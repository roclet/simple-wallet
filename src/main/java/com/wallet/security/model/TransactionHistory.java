package com.wallet.security.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document("transaction_history")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TransactionHistory {
    @Id
    private String transactionId;
    private String userId;
    private String accountNumber;
    private String transactionType;
    private BigDecimal transactionAmount;
    private String transactionDateTime;
 }
