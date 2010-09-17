package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.FeatureClassDao;
import org.jason.mapmaker2.model.FeatureClass;

/**
 * @author TSgt Jason Ferguson
 */
public class HibernateFeatureClassDao extends HibernateGenericDao<FeatureClass> implements FeatureClassDao {

    public HibernateFeatureClassDao() {
        super(FeatureClass.class);
    }
}
