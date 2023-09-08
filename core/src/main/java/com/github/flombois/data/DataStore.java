package com.github.flombois.data;

/**
 * Simple datastore contract
 *
 * @param <T> Type of stored data
 */
public interface DataStore<T> {

    boolean add(T data);

    boolean contains(T data);

    void clear();
}
