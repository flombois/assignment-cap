package process;

import com.github.flombois.process.ProcessingResult;
import com.github.flombois.process.RecordProcessor;
import com.github.flombois.data.Record;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public interface RecordProcessorTest<R extends Record> {

    RecordProcessor<R> newRecordProcessor();

    Collection<R> newRecords();

    @Test
    default void givenRecords_whenProcess_thenReturnResults() {
        Collection<R> records = newRecords();
        Collection<ProcessingResult<R>> results = newRecordProcessor().process(records);
        assertNotNull(results);
        assertEquals(records.size(), results.size());
    }
}
