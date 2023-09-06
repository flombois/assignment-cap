package com.github.flombois.validators;

import com.github.flombois.data.DataStore;
import com.github.flombois.data.Record;

public class DuplicateTransactionValidator implements RecordValidator<Record> {
    private DataStore<Integer> store;

    public DuplicateTransactionValidator(DataStore<Integer> store) {
        setStore(store);
    }

    @Override
    public ValidationResult<Record> validate(Record record) {
        if(store.contains(record.getReference())) {
            return ValidationResult.error(record, String.format("Transaction with reference %s has already been " +
                    "processed", record.getReference()));
        }
        store.add(record.getReference());
        return ValidationResult.success(record);
    }

    protected DataStore<Integer> getStore() {
        return store;
    }

    public void setStore(DataStore<Integer> store) {
        this.store = store;
    }
}
