package com.github.flombois.validators;

import com.github.flombois.data.Record;

import java.math.BigDecimal;

public class BalanceValidator implements RecordValidator<Record> {

    @Override
    public ValidationResult<Record> validate(Record record) {
        // Compute expected end balance
        BigDecimal computedBalance = record.getStartBalance().add(record.getMutation());
        if (!areEquals(computedBalance, record.getEndBalance())) {
            return ValidationResult.error(record, String.format(
                    "Transaction %s balance is invalid, expected end balance to be %s but is %s",
                    record.getReference(), record.getEndBalance(), computedBalance));
        }
        return ValidationResult.success(record);
    }

    private boolean areEquals(BigDecimal a, BigDecimal b) {
        return a.setScale(Record.SCALE, Record.ROUNDING_MODE).compareTo(b.setScale(Record.SCALE, Record.ROUNDING_MODE)) == 0;
    }
}
