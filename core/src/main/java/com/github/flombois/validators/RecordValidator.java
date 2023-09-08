package com.github.flombois.validators;

import com.github.flombois.data.Record;

/**
 * General contract for record validator
 *
 * @param <R> The type of record to validate
 */
public interface RecordValidator<R extends Record> {

    /**
     * Validate record
     *
     * @param record Record to validate
     * @return A valid result if validation succeed, invalid otherwise
     */
    ValidationResult<R> validate(R record);
}
