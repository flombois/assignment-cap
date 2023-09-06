package com.github.flombois.validators;

public record ValidationError(String errorMessage) {

    public static final ValidationError NO_ERROR = new ValidationError("");

    public static ValidationError of(String errorMessage) {
        return new ValidationError(errorMessage);
    }

}
