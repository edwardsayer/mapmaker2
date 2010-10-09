package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.Collection;
import java.util.List;

/**
 * GenericDAO for all other Hibernate DAOs to extend.
 * <p/>
 * Classes wishing to make use of QBE should minimize use of primitives over Object wrappers.
 *
 * @author Jason Ferguson
 */
public class HibernateGenericDao<T> extends HibernateDaoSupport implements GenericDao<T> {

    public Class<T> type;

    public HibernateGenericDao(Class<T> type) {
        this.type = type;
    }

    @Autowired
    public void init(HibernateTemplate hibernateTemplate) {
        setHibernateTemplate(hibernateTemplate);
    }

    //@Transactional
    public T save(T obj) {

        Integer id = (Integer) getSession().save(obj);
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

    @SuppressWarnings("unchecked")
    public T getById(int id) {

        return (T) getSession().get(type, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> queryByExample(T exampleObj) {

        return getHibernateTemplate().findByExample(exampleObj);
    }

    public void saveAll(Collection<T> objList) {
        throw new UnsupportedOperationException();
    }
}
