package com.github.flombois.parsing;

import com.github.flombois.data.Record;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XMLParser<T extends Record> implements DataParser<T> {

    @Override
    public List<T> parse(InputStream inputStream) throws ParsingException {
        return null;
    }
}
