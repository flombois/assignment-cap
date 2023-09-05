package com.github.flombois.validators;

import com.github.flombois.data.DataStore;
import com.github.flombois.data.Record;

public class DuplicateTransactionValidator implements RecordValidator<Record> {

    private DataStore<Integer> store;

    @Override
    public ValidationResult<Record> validate(Record record) {
        if(store.contains(record.getTransactionReference())) {
            return ValidationResult.error(record, String.format("Transaction with reference %s has already been " +
                    "processed", record.getTransactionReference()));
        }
        store.add(record.getTransactionReference());
        return ValidationResult.success(record);
    }

    public DataStore<Integer> getStore() {
        return store;
    }

    public void setStore(DataStore<Integer> store) {
        this.store = store;
    }
}
