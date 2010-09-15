package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.GenericDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public class GenericHibernateDao<T> extends HibernateDaoSupport implements GenericDao<T> {

    public Class<T> clazz;

    public T save(T obj) {

        Integer id = (Integer) getHibernateTemplate().save(obj);
        return getById(id);
    }

    public void update(T obj) {

        getHibernateTemplate().update(obj);
    }

    public void delete(T obj) {
        getHibernateTemplate().delete(obj);
    }

    public List<T> getAll() {

        return getHibernateTemplate().loadAll(clazz);
    }

    public T getById(int id) {

        return getHibernateTemplate().get(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByExample(T exampleObj) {

        return getHibernateTemplate().findByExample(exampleObj);
    }
}
