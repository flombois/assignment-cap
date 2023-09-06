package com.github.flombois.parsing;

import com.github.flombois.data.Record;


public class RecordCsvParserTest implements DataParserTest<Record> {

    @Override
    public DataParser<Record> newParser() {
        return new RecordCsvParser();
    }

    @Override
    public String inputFileName() {
        return "/records.csv";
    }

}
