package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.FeatureTypeDescriptionDao;
import org.jason.mapmaker2.model.FeatureTypeDescription;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("featureTypeDescriptionDao")
public class HibernateFeatureTypeDescriptionDao extends HibernateGenericDao<FeatureTypeDescription> implements FeatureTypeDescriptionDao {

    public HibernateFeatureTypeDescriptionDao() {
        super(FeatureTypeDescription.class);
    }
}
