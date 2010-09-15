package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.GenericDao;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public class HibernateGenericDao<T> extends HibernateDaoSupport implements GenericDao<T> {

    public Class<T> type;

    public HibernateGenericDao(Class<T> type) {
        this.type = type;
    }

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

        return getHibernateTemplate().loadAll(type);
    }

    public T getById(int id) {

        return getHibernateTemplate().get(type, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByExample(T exampleObj) {

        return getHibernateTemplate().findByExample(exampleObj);
    }
}
