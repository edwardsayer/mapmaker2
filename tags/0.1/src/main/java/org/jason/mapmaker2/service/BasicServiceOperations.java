package org.jason.mapmaker2.service;

import java.util.List;

/**
 * BasicServiceOperations.java
 * 
 * @author Jason Ferguson
 */
public interface BasicServiceOperations<T> {

    T save(T obj);

    void update(T obj);

    void delete(T obj);

    List<T> getAll();

    T getById(int id);
}
