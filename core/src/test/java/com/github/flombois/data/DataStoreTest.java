package com.github.flombois.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface DataStoreTest<T> {

    DataStore<T> newDataStore();

    T newData();

    @Test
    default void givenEmptyStore_whenAdd_thenReturnTrue() {
        assertTrue(newDataStore().add(newData()));
    }

    @Test
    default void givenStoreWithData_whenContains_thenReturnTrue() {
        DataStore<T> dataStore = newDataStore();
        T data = newData();
        dataStore.add(data);

        assertTrue(dataStore.contains(data));
    }

    @Test
    default void givenEmptyStore_whenContains_thenReturnFalse() {
        DataStore<T> dataStore = newDataStore();
        T data = newData();

        assertFalse(dataStore.contains(data));
    }

    @Test
    default void givenEmptyStore_whenClear_thenDataStoreIsEmpty() {
        DataStore<T> dataStore = newDataStore();
        T data = newData();
        dataStore.add(data);
        dataStore.clear();

        assertFalse(dataStore.contains(data));
    }
}
