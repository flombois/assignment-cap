package com.github.flombois.process;

import com.github.flombois.data.HashSetDataStore;
import com.github.flombois.data.Record;
import com.github.flombois.validators.BalanceValidator;
import com.github.flombois.validators.DuplicateTransactionValidator;
import com.github.flombois.validators.RecordValidator;
import com.github.flombois.validators.ValidationResult;

import java.util.List;

/**
 * Validate records in this order:
 * First check duplicated reference
 * Then check balance
 */
public class RecordValidationProcessor implements RecordProcessor<Record> {

    private final List<RecordValidator<Record>> validators = List.of(
            new DuplicateTransactionValidator(new HashSetDataStore<>()),
            new BalanceValidator()
    );

    @Override
    public List<ProcessingResult<Record>> process(List<Record> records) {
        // Process the record List and place each result in an unmodifiable list.
        return records.stream().map(this::process).toList();
    }

    /**
     * Process a single record, if the first validation result is invalid then validation process stops and
     * validation result is returned immediately.
     * @param record The record to process
     * @return The last valid result or the first invalid result
     */
    protected ProcessingResult<Record> process(Record record) {
        ValidationResult<Record> result = null;

        // Loop through the validators and return last valid result unless validation fails
        // in that case stop the loop and return the result immediately
        for(RecordValidator<Record> validator: validators) {
            result = validator.validate(record);
            if(!result.isValid()) break;
        }
        return result;
    }
}
