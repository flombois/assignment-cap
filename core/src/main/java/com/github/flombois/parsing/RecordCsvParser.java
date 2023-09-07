package com.github.flombois.parsing;

import com.github.flombois.data.Record;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.MappingStrategy;

/**
 * Extend {@link CsvParser} to parse {@link Record}
 */
public class RecordCsvParser extends CsvParser<Record> {

    private static final MappingStrategy<Record> MAPPING_STRATEGY;

    // Use annotations to bind Record properties to CSV columns
    static {
        MAPPING_STRATEGY = new ColumnPositionMappingStrategy<>();
        MAPPING_STRATEGY.setType(Record.class);
    }

    public RecordCsvParser() {
        super(MAPPING_STRATEGY);
    }
}
