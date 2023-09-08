package com.github.flombois.process;


/**
 * Result produced by a {@link RecordProcessor}
 *
 * @param <T> Type of processed record
 */
public abstract class ProcessingResult<T> {

    private final T record;

    /**
     * Constructor
     *
     * @param record Reference to the processed record
     */
    protected ProcessingResult(T record) {
        this.record = record;
    }

    public T getRecord() {
        return record;
    }
}
