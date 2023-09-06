package com.github.flombois;

import com.github.flombois.data.Record;
import com.github.flombois.parsing.DataParser;
import com.github.flombois.parsing.RecordCsvParser;
import com.github.flombois.parsing.RecordXmlParser;

import java.util.Collections;
import java.util.List;

public enum InputFormat {

    XML(List.of("text/xml", "application/xml")) {
        @Override
        public DataParser<Record> dataParser() {
            return new RecordXmlParser();
        }
    },
    CSV(List.of("text/csv")) {
        @Override
        public DataParser<Record> dataParser() {
            return new RecordCsvParser();
        }
    },
    UNSUPPORTED(Collections.emptyList()) {
        @Override
        public DataParser<Record> dataParser() {
            throw new UnsupportedOperationException();
        }
    };

    final List<String> supportedMimeTypes;

    InputFormat(List<String> supportedMimeTypes) {
        this.supportedMimeTypes = supportedMimeTypes;
    }

    public List<String> getSupportedMimeTypes() {
        return supportedMimeTypes;
    }

    public abstract DataParser<Record> dataParser();
}
