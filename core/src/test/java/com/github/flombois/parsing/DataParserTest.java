package com.github.flombois.parsing;

import com.github.flombois.data.Record;
import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public interface DataParserTest<T> {

    DataParser<T> newParser();

    String inputFileName();

    @Test
    default void givenSamples_whenParse_thenReturnRecords() throws IOException, ParsingException {
        DataParser<T> recordXmlParser = newParser();
        List<T> records;
        try(InputStream stream = new BufferedInputStream(Objects.requireNonNull(getClass()
                .getResourceAsStream(inputFileName())))) {
            records = recordXmlParser.parse(stream);
        }

        assertNotNull(records);
        assertEquals(10, records.size());
    }
}
