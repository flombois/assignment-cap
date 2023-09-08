package com.github.flombois.process;

import java.util.List;

/**
 * Process records
 *
 * @param <T> Type of processed records
 */
public interface RecordProcessor<T> {

    /**
     * Process records
     *
     * @param records The records to process
     * @return The processing results as a list
     */
    List<ProcessingResult<T>> process(List<T> records);


}
