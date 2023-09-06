package com.github.flombois.reporting;

import com.github.flombois.process.ProcessingResult;

import java.util.List;

public interface Report<T extends Reportable> {

    String report(List<T> results);
}
