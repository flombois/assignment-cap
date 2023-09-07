package com.github.flombois.parsing;

/**
 * Custom exception to encapsulate exception thrown while parsing
 */
public class ParsingException extends Exception {

    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(Throwable cause) {
        super(cause);
    }

}
