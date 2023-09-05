package process;

import com.github.flombois.data.Record;
import com.github.flombois.process.ProcessingResult;
import com.github.flombois.process.RecordProcessor;
import com.github.flombois.process.RecordValidationProcessor;
import com.github.flombois.validators.ValidationResult;
import org.junit.jupiter.api.Test;
import validators.RecordUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class RecordValidationProcessorTest implements RecordProcessorTest<Record> {

    @Override
    public RecordProcessor<Record> newRecordProcessor() {
        return new RecordValidationProcessor();
    }

    @Override
    public Collection<Record> newRecords() {
        return IntStream.range(0, 10).mapToObj(RecordUtils::withTransactionReference).toList();
    }

    @Test
    public void givenOnlyValidRecords_whenProcess_thenReturnOnlyValidResults() {
        List<Record> records = IntStream.range(0, 10).mapToObj(RecordUtils::randomValid).toList();
        Collection<ProcessingResult<Record>> results = new RecordValidationProcessor().process(records);
        for (ProcessingResult<Record> result : results) {
           assertNotNull(result);
           assertTrue(((ValidationResult<Record>)result).isValid());
        }
    }

    @Test
    public void givenOnlyInvalidRecords_whenProcess_thenReturnOnlyInvalidResults() {
        List<Record> records = IntStream.range(0, 10).mapToObj(RecordUtils::randomInvalid).toList();
        Collection<ProcessingResult<Record>> results = new RecordValidationProcessor().process(records);
        for (ProcessingResult<Record> result : results) {
            assertNotNull(result);
            assertFalse(((ValidationResult<Record>)result).isValid());
        }
    }
}
