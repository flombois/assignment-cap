package com.github.flombois.validators;

import com.github.flombois.data.Record;

import com.github.flombois.process.ProcessingResult;
import com.github.flombois.reporting.Reportable;

public class ValidationResult<R extends Record> extends ProcessingResult<R> implements Reportable {

    private final ValidationError validationError;

    /**
     * Constructor
     * @param record A valid record
     */
    private ValidationResult(R record) {
        super(record);
        this.validationError = ValidationError.NO_ERROR;
    }

    /**
     * Constructor
     * @param record an invalid record
     * @param validationError The corresponding validation error
     */
    private ValidationResult(R record, ValidationError validationError) {
        super(record);
        this.validationError = validationError;
    }

    /**
     * Create a valid result
     * @param record The validated record
     * @return a valid result
     * @param <R> The type of record
     */
    public static <R extends Record> ValidationResult<R> success(R record) {
        return new ValidationResult<>(record);
    }

    /**
     * Create an invalid result
     * @param record The validated record
     * @param errorMessage The corresponding error message
     * @return an invalid result
     * @param <R> The type of record
     */
    public static <R extends Record> ValidationResult<R> error(R record, String errorMessage) {
        return new ValidationResult<>(record, ValidationError.of(errorMessage));
    }

    public boolean isValid() {
        // Reference check
        return validationError == ValidationError.NO_ERROR;
    }

    @Override
    public String report() {
        if (isValid())
            return String.format("Transaction %s is valid", getRecord().getReference());
        return String.format("Transaction %s is invalid:%n\t%s", getRecord().getReference(),
                getValidationError().errorMessage());
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
