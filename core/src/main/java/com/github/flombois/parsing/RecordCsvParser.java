package com.github.flombois.parsing;

import com.github.flombois.data.Record;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.MappingStrategy;

public class RecordCsvParser extends CsvParser<Record> {

    private static final MappingStrategy<Record> MAPPING_STRATEGY;

    static {
        MAPPING_STRATEGY = new ColumnPositionMappingStrategy<>();
        MAPPING_STRATEGY.setType(Record.class);
    }

    public RecordCsvParser() {
        super(MAPPING_STRATEGY);
    }
}
