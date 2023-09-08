package com.github.flombois.process;

import com.github.flombois.data.Record;
import com.github.flombois.validators.RecordUtils;
import com.github.flombois.validators.ValidationResult;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class RecordValidationProcessorTest implements RecordProcessorTest<Record> {

    @Override
    public RecordProcessor<Record> newRecordProcessor() {
        return new RecordValidationProcessor();
    }

    @Override
    public List<Record> newRecords() {
        return IntStream.range(0, 10).mapToObj(RecordUtils::withTransactionReference).toList();
    }

    @Test
    public void givenOnlyValidRecords_whenProcess_thenReturnOnlyValidResults() {
        List<Record> records = IntStream.range(0, 10).mapToObj(RecordUtils::randomValid).toList();
        List<ProcessingResult<Record>> results = new RecordValidationProcessor().process(records);
        for (ProcessingResult<Record> result : results) {
            assertNotNull(result);
            assertTrue(((ValidationResult<Record>) result).isValid());
        }
    }

    @Test
    public void givenOnlyInvalidRecords_whenProcess_thenReturnOnlyInvalidResults() {
        List<Record> records = IntStream.range(0, 10).mapToObj(RecordUtils::randomInvalid).toList();
        List<ProcessingResult<Record>> results = new RecordValidationProcessor().process(records);
        for (ProcessingResult<Record> result : results) {
            assertNotNull(result);
            assertFalse(((ValidationResult<Record>) result).isValid());
        }
    }
}
