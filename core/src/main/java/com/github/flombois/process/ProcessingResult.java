package com.github.flombois.process;

import com.github.flombois.data.Record;

public abstract class ProcessingResult<T> {

    private final T record;

    protected ProcessingResult(T record) {
        this.record = record;
    }

    public T getRecord() {
        return record;
    }
}
