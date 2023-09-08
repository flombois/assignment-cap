package com.github.flombois.parsing;

import java.io.InputStream;
import java.util.List;

/**
 * Simple contract to parse data from an {@link InputStream}
 *
 * @param <T> Type of the parsed items
 */
public interface DataParser<T> {

    /**
     * Parse the input stream and close it
     *
     * @param inputStream The input stream
     * @return The parsed data as a list of items
     * @throws ParsingException Thrown if an error occurs while parsing
     */
    List<T> parse(InputStream inputStream) throws ParsingException;

}
