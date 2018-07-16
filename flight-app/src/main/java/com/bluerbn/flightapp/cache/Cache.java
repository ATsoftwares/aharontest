package com.bluerbn.flightapp.cache;

/**
 * @author Aharon Bar-El
 */
public interface Cache<K, V> {

    void add(K key, V value);

    void remove(K key);

    V get(K key);

    void clear();

    int size();
}
