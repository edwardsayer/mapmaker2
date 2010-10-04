package org.jason.mapmaker2.dao.hibernate;

import org.hibernate.CacheMode;
import org.hibernate.Transaction;
import org.jason.mapmaker2.dao.CustomFeatureDao;
import org.jason.mapmaker2.model.CustomFeature;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Jason Ferguson
 */
@Repository("customFeatureDao")
public class HibernateCustomFeatureDao extends HibernateGenericDao<CustomFeature> implements CustomFeatureDao {

    private static final int batchSize = 100;

    public HibernateCustomFeatureDao() {
        super(CustomFeature.class);
    }

    @Override
    public void saveAll(Collection<CustomFeature> objList) {

        getSession().setCacheMode(CacheMode.IGNORE);
        //log.debug("Saving " + objList.size() + " BorderPoints");
        Transaction tx = getSession().beginTransaction();

        int counter = 1;
        for (CustomFeature cf : objList) {
            getSession().save(cf);
            counter++;
            if (counter == batchSize) {
                //log.info("Commiting " + batchSize + " Custom Features");
                getSession().flush();
                getSession().clear();
                counter = 1;
            }
        }

        tx.commit();

        getSession().setCacheMode(CacheMode.NORMAL);
    }
}
