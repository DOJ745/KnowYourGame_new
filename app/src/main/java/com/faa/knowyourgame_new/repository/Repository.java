package com.faa.knowyourgame_new.repository;

public interface Repository<T> {

    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void remove(T item);

    /*void remove(Specification specification);

    List<T> query(Specification specification);*/
}
