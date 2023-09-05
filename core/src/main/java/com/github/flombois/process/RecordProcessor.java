package com.github.flombois.process;

import com.github.flombois.data.Record;

import java.util.Collection;


public interface RecordProcessor<R extends Record> {

    Collection<ProcessingResult<R>> process(Collection<R> records);


}
