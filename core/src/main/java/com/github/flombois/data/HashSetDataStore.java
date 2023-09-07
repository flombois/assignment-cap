package com.github.flombois.data;

import java.util.HashSet;

/**
 * Implementation of {@link DataStore} using a {@link HashSet}
 * @param <T> Type of stored data
 */
public class HashSetDataStore<T> implements DataStore<T> {

    private final HashSet<T> hashSet = new HashSet<>();

    @Override
    public boolean add(T data) {
        return hashSet.add(data);
    }

    @Override
    public boolean contains(T data) {
        return hashSet.contains(data);
    }

    @Override
    public void clear() {
        hashSet.clear();
    }
}
