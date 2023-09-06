package com.github.flombois.process;

import com.github.flombois.data.Record;

import java.util.List;


public interface RecordProcessor<T> {

    List<ProcessingResult<T>> process(List<T> records);


}
