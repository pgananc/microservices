package com.pichincha.account.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.pichincha.account.model.AccountType;

import lombok.Data;
@Data
public class TransactionReportDTO {

    private LocalDate createdOn;

    private String clientName;

    private String accountNumber;

    private AccountType accountType;

    private BigDecimal openingBalance;

    private Boolean accountStatus;

    private BigDecimal amount;

    private BigDecimal balance;

    
}
