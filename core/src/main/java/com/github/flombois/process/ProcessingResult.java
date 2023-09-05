package com.github.flombois.process;

import com.github.flombois.data.Record;

public abstract class ProcessingResult<R extends Record> {

    private final R record;

    protected ProcessingResult(R record) {
        this.record = record;
    }

    public R getRecord() {
        return record;
    }
}
