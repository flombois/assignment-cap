package com.github.flombois.parsing;

import com.github.flombois.data.Record;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface DataParser<T extends Record> {

    List<T> parse(InputStream inputStream) throws ParsingException;


}
