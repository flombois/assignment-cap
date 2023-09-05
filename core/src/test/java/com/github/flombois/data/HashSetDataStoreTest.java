package com.github.flombois.data;

import java.util.Random;

public class HashSetDataStoreTest<T> implements DataStoreTest<T> {

    @Override
    public DataStore<T> newDataStore() {
        return new HashSetDataStore<>();
    }

    // Warning can be safely removed here
    @SuppressWarnings("unchecked")
    @Override
    public T newData() {
        return (T) Double.valueOf(Math.random());
    }
}
