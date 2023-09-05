package com.github.flombois.data;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Record {

    public final static int SCALE = 2;
    public final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

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
        this.startBalance = startBalance.setScale(SCALE, ROUNDING_MODE);
        this.mutation = mutation.setScale(SCALE, ROUNDING_MODE);
        this.endBalance = endBalance.setScale(SCALE, ROUNDING_MODE);
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
