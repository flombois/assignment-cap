package com.github.flombois.validators;

import com.github.flombois.data.Record;

import com.github.flombois.process.ProcessingResult;
import com.github.flombois.reporting.Reportable;
import jakarta.validation.constraints.NotNull;

public class ValidationResult<R extends Record> extends ProcessingResult<R> implements Reportable {

    private final ValidationError validationError;

    private ValidationResult(R record) {
        super(record);
        this.validationError = ValidationError.NO_ERROR;
    }

    private ValidationResult(R record, ValidationError validationError) {
        super(record);
        this.validationError = validationError;
    }

    public static <R extends Record> ValidationResult<R> success(@NotNull R record) {
        return new ValidationResult<>(record);
    }

    public static <R extends Record> ValidationResult<R> error(@NotNull R record, @NotNull String errorMessage) {
        return new ValidationResult<>(record, new ValidationError(errorMessage));
    }

    public boolean isValid() {
        // Reference check
        return validationError == ValidationError.NO_ERROR;
    }

    @Override
    public String report() {
        if (isValid())
            return String.format("Transaction %s is valid", getRecord().getTransactionReference());
        return String.format("Transaction %s is invalid:\n\t%s", getRecord().getTransactionReference(),
                getValidationError().errorMessage());
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
