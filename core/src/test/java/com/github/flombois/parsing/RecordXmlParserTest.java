package com.github.flombois.parsing;

import com.github.flombois.data.Record;

public class RecordXmlParserTest implements DataParserTest<Record> {

    @Override
    public DataParser<Record> newParser() {
        return new RecordXmlParser();
    }

    @Override
    public String inputFileName() {
        return "/records.xml";
    }

}
