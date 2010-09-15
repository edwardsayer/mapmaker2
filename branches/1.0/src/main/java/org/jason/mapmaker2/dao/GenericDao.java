package org.jason.mapmaker2.dao;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public interface GenericDao<T> {

    /**
     * Persist an object
     *
     * @param obj
     * @return  persisted object
     */
    T save(T obj);

    /**
     * Update an already persisted object
     *
     * @param obj
     */
    void update(T obj);

    /**
     * Delete a persisted object
     *
     * @param obj
     */
    void delete(T obj);

    /**
     * Return a list of all persisted objects of the given type
     * @return
     */
    List<T> getAll();

    /**
     * Return a persisted object by its identifier
     *
     * @param id
     * @return
     */
    T getById(int id);

    /**
     * Return a List of objects matching an example
     * 
     * @param exampleObj
     * @return
     */
    List<T> queryByExample(T exampleObj);
}
