package com.github.flombois.data;

public interface DataStore<T> {

    boolean add(T data);

    boolean contains(T data);

    void clear();
}
