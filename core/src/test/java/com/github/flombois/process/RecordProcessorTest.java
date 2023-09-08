package com.github.flombois.process;

import com.github.flombois.data.Record;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public interface RecordProcessorTest<R extends Record> {

    RecordProcessor<R> newRecordProcessor();

    List<R> newRecords();

    @Test
    default void givenRecords_whenProcess_thenReturnResults() {
        List<R> records = newRecords();
        List<ProcessingResult<R>> results = newRecordProcessor().process(records);
        assertNotNull(results);
        assertEquals(records.size(), results.size());
    }
}
