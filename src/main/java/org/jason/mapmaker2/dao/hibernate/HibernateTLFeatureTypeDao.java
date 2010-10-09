package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.TLFeatureTypeDao;
import org.jason.mapmaker2.model.TLFeatureType;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("TLFeatureTypeDao")
public class HibernateTLFeatureTypeDao extends HibernateGenericDao<TLFeatureType> implements TLFeatureTypeDao {

    public HibernateTLFeatureTypeDao() {
        super(TLFeatureType.class);
    }
}
