package com.github.flombois.validators;

import com.github.flombois.data.Record;

public interface RecordValidator<R extends Record> {

    ValidationResult<R> validate(R record);
}
