package com.github.flombois.reporting;

import java.util.List;

/**
 * Create reports
 *
 * @param <T> Type of reported records
 */
public interface Report<T extends Reportable> {

    /**
     * Create a string from a list of reportable results
     *
     * @param results Results
     * @return A string containing the reported results
     */
    String report(List<T> results);
}
