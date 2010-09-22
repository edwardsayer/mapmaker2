package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.TigerFeatureTypeDao;
import org.jason.mapmaker2.model.TigerFeatureType;
import org.springframework.stereotype.Repository;

/**
 * @author TSgt Jason Ferguson
 */
@Repository("tigerFeatureTypeDao")
public class HibernateTigerFeatureTypeDao extends HibernateGenericDao<TigerFeatureType> implements TigerFeatureTypeDao {

    public HibernateTigerFeatureTypeDao() {
        super(TigerFeatureType.class);
    }
}
