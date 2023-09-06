package com.github.flombois.reporting;

import com.github.flombois.data.Record;
import com.github.flombois.validators.ValidationResult;

import java.util.List;
import java.util.stream.Collectors;

public class InvalidRecordsReport implements Report<ValidationResult<Record>> {
    @Override
    public String report(List<ValidationResult<Record>> results) {
        return results.stream()
                .filter(r -> !r.isValid())
                .map(ValidationResult::report)
                .collect(Collectors.joining("\n"));
    }
}
