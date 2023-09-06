package com.github.flombois.process;

import com.github.flombois.data.Record;

import java.util.List;


public interface RecordProcessor<R extends Record> {

    List<ProcessingResult<R>> process(List<R> records);


}
