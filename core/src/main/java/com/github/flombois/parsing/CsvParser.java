package com.github.flombois.parsing;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.MappingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Parse data from a CSV formatted input stream
 * This implementation relies on the <a href="https://mvnrepository.com/artifact/com.opencsv/opencsv">opencsv</a>
 * library
 * @param <T> Type of the parsed items
 */
public class CsvParser<T> implements DataParser<T> {

    private MappingStrategy<T> mappingStrategy;

    /**
     * Constructor
     * @param mappingStrategy @see MappingStrategy
     */
    public CsvParser(MappingStrategy<T> mappingStrategy) {
        this.mappingStrategy = mappingStrategy;
    }

    /**
     * Parse the input stream and close it
     * @param inputStream The input stream
     * @return The parsed data as a list of items
     * @throws ParsingException Thrown if an error occurs while parsing
     */
    @Override
    public List<T> parse(InputStream inputStream) throws ParsingException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        // Skip header, assumption is made that input file always contains header according to sample
        CSVReaderBuilder builder = new CSVReaderBuilder(bufferedReader).withSkipLines(1);
        List<T> records;

        // Inner reader/stream will also be closed
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
