package com.github.flombois.parsing;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public interface DataParserTest<T> {

    DataParser<T> newParser();

    String inputFileName();

    @Test
    default void givenSamples_whenParse_thenReturnRecords() throws IOException, ParsingException {
        DataParser<T> recordXmlParser = newParser();
        List<T> records;
        try (InputStream stream = new BufferedInputStream(Objects.requireNonNull(getClass()
                .getResourceAsStream(inputFileName())))) {
            records = recordXmlParser.parse(stream);
        }

        assertNotNull(records);
        assertEquals(10, records.size());
    }

    // Ensure XML parser properly close the input stream
    @Test
    default void givenSamples_whenParse_thenCloseInputStream() throws ParsingException {
        DataParser<T> recordXmlParser = newParser();
        ClosedAwareInputStream stream = new ClosedAwareInputStream(
                new BufferedInputStream(Objects.requireNonNull(getClass()
                        .getResourceAsStream(inputFileName()))));

        assertFalse(stream.isCloseCalled());
        recordXmlParser.parse(stream);
        assertTrue(stream.isCloseCalled());
    }

    class ClosedAwareInputStream extends BufferedInputStream {

        private boolean isClosedCalled = false;

        public ClosedAwareInputStream(InputStream in) {
            super(in);
        }

        @Override
        public void close() throws IOException {
            super.close();
            isClosedCalled = true;
        }

        public boolean isCloseCalled() {
            return isClosedCalled;
        }
    }
}
