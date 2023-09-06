package com.github.flombois.reporting;

import com.github.flombois.data.Record;
import com.github.flombois.validators.RecordUtils;
import com.github.flombois.validators.ValidationResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidRecordsReportTest {

    @Test
    public void givenValidationResults_whenReport_thenReturnString() {
        List<ValidationResult<Record>> results = List.of(
                ValidationResult.error(RecordUtils.withTransactionReference(0), "error0"),
                ValidationResult.success(RecordUtils.withTransactionReference(1)),
                ValidationResult.error(RecordUtils.withTransactionReference(2), "error2")
        );

        String report = new InvalidRecordsReport().report(results);

        assertNotNull(report);
        assertTrue(report.contains("Transaction 0 is invalid"));
        assertTrue(report.contains("error0"));
        assertFalse(report.contains("Transaction 1 is invalid"));
        assertFalse(report.contains("error1"));
        assertTrue(report.contains("Transaction 2 is invalid"));
        assertTrue(report.contains("error2"));

    }
}
