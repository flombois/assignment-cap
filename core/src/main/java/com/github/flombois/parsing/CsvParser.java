package com.github.flombois.parsing;

import com.github.flombois.data.Record;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.MappingStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvParser<T extends Record> implements DataParser<T> {

    private MappingStrategy<T> mappingStrategy;

    public CsvParser(MappingStrategy<T> mappingStrategy) {
        this.mappingStrategy = mappingStrategy;
    }

    @Override
    public List<T> parse(InputStream inputStream) throws ParsingException {
        // Skip header, assumption is made that input file always contains header according to sample
        CSVReaderBuilder builder = new CSVReaderBuilder(new InputStreamReader(inputStream))
                .withSkipLines(1);
        List<T> records;
        try (CSVReader reader = builder.build()) {
            CsvToBean<T> beanConverter = new CsvToBean<>();
            beanConverter.setCsvReader(reader);
            beanConverter.setMappingStrategy(getMappingStrategy());
            records = beanConverter.parse();
        } catch (IOException e) {
            throw new ParsingException(e);
        }
        return records;
    }

    public MappingStrategy<T> getMappingStrategy() {
        return mappingStrategy;
    }

    public void setMappingStrategy(MappingStrategy<T> mappingStrategy) {
        this.mappingStrategy = mappingStrategy;
    }
}
