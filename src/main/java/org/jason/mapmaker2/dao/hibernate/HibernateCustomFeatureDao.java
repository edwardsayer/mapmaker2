package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.CustomFeatureDao;
import org.jason.mapmaker2.model.CustomFeature;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("customFeatureDao")
public class HibernateCustomFeatureDao extends HibernateGenericDao<CustomFeature> implements CustomFeatureDao {

    public HibernateCustomFeatureDao() {
        super(CustomFeature.class);
    }

}
