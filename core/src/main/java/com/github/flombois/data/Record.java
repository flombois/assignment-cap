package com.github.flombois.data;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class Record {

    @NotNull
    private final int transactionReference;
    @NotNull
    private final String accountNumber;
    @NotNull
    private final String description;
    @NotNull
    private final BigDecimal startBalance;
    @NotNull
    private final BigDecimal mutation;
    @NotNull
    private final BigDecimal endBalance;

    public Record(int transactionReference, String accountNumber, String description,
                  BigDecimal startBalance, BigDecimal mutation, BigDecimal endBalance) {
        this.transactionReference = transactionReference;
        this.accountNumber = accountNumber;
        this.description = description;
        this.startBalance = startBalance;
        this.mutation = mutation;
        this.endBalance = endBalance;
    }

    public int getTransactionReference() {
        return transactionReference;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getStartBalance() {
        return startBalance;
    }

    public BigDecimal getMutation() {
        return mutation;
    }

    public BigDecimal getEndBalance() {
        return endBalance;
    }
}
