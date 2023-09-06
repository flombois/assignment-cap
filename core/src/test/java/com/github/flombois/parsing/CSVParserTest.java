package com.github.flombois.parsing;

import org.junit.jupiter.api.Test;
import com.github.flombois.data.Record;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CSVParserTest {

    @Test
    public void givenSamples_whenParse_thenReturnRecords() throws IOException, ParsingException {
        CsvParser<Record> recordCSVParser = new RecordCsvParser();
        List<Record> expectedRecords;
        try(InputStream stream = new BufferedInputStream(Objects.requireNonNull(getClass()
                .getResourceAsStream("/records.csv")))) {
            expectedRecords = recordCSVParser.parse(stream);
        }

        assertNotNull(expectedRecords);
        assertEquals(10, expectedRecords.size());


    }

}
