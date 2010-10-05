package org.jason.mapmaker2.dao.hibernate;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.jason.mapmaker2.dao.CustomFeatureDao;
import org.jason.mapmaker2.model.CustomFeature;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

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

    @SuppressWarnings("unchecked")
    public List<CustomFeature> getCustomFeatures(float minLat, float maxLat, float minLng, float maxLng) {

        String hql = "from CustomFeature cf where cf.latitude >= :minLat and cf.latitude <= :maxLat and " +
                "cf.longtiude >= :minLng and cf.longitude <= :maxLng";

        Query query = getSession().createQuery(hql);
        query.setFloat("minLat", minLat);
        query.setFloat("maxLat", maxLat);
        query.setFloat("minLng", minLng);
        query.setFloat("maxLng", maxLng);

        return (List<CustomFeature>) query.list();
    }

    @SuppressWarnings("unchecked")
    public List<CustomFeature> getCustomFeatures(float minLat, float maxLat, float minLng, float maxLng, List<String> typeList) {

        String hql = "from CustomFeature cf where cf.latitude >= :minLat and cf.latitude <= :maxLat and " +
                "cf.longtiude >= :minLng and cf.longitude <= :maxLng and cf.featureClass in (:featureClassList)";

        Query query = getSession().createQuery(hql);
        query.setFloat("minLat", minLat);
        query.setFloat("maxLat", maxLat);
        query.setFloat("minLng", minLng);
        query.setFloat("maxLng", maxLng);
        query.setParameterList("featureClassList", typeList);

        return (List<CustomFeature>) query.list();

    }
}
