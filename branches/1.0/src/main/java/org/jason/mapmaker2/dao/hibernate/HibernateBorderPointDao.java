package org.jason.mapmaker2.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.jason.mapmaker2.dao.BorderPointDao;
import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jason Ferguson
 */
@Repository("borderPointDao")
public class HibernateBorderPointDao extends HibernateGenericDao<BorderPoint> implements BorderPointDao {

    private static final Log log = LogFactory.getLog(HibernateBorderPointDao.class);

    private static final int batchSize = 100;

    public HibernateBorderPointDao() {
        super(BorderPoint.class);
    }

    @Override
    public void saveAll(Collection<BorderPoint> objList) {

        getSession().setCacheMode(CacheMode.IGNORE);
        log.debug("Saving " + objList.size() + " BorderPoints");
        Transaction tx = getSession().beginTransaction();

        int counter = 1;
        for (BorderPoint bp : objList) {
            getSession().save(bp);
            counter++;
            if (counter == batchSize) {
                log.info("Commiting " + batchSize + " BorderPoints");
                getSession().flush();
                getSession().clear();
                counter = 1;
            }
        }

        tx.commit();

        getSession().setCacheMode(CacheMode.NORMAL);
    }

    public Map<String, Float> getBoundingBox(StateCode stateCode, SubCode subCode) {

        Map<String, Float> resultMap = new HashMap<String, Float>();

        // I didn't want to do a "select new map()" here because I'd rather deal with the casting from Object to
        // Float here instead of at the service (or higher) layer
        String hql = "select min(bp.latitude), max(bp.latitude), min(bp.longitude), max(bp.longitude) from " +
                "BorderPoint bp where bp.stateCode = :stateCode and bp.subCode = :subCode";
        Query query = getSession().createQuery(hql);
        query.setParameter("stateCode", stateCode);
        query.setParameter("subCode", subCode);

        Object[] result = (Object[]) query.uniqueResult();
        for (Object o: result) {
            resultMap.put("minLat", (Float) result[0]);
            resultMap.put("maxLat", (Float) result[1]);
            resultMap.put("minLng", (Float) result[2]);
            resultMap.put("maxLng", (Float) result[3]);
        }

        return resultMap;
    }

    @SuppressWarnings("unchecked")
    public Float getMinimumLatitude(final StateCode stateCode, final SubCode subCode) {

        List<Float> results = (List<Float>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                String hql = "select min(bp.latitude) from BorderPoint bp where bp.stateCode = :stateCode and bp.subCode = :subCode";
                Query query = session.createQuery(hql);
                query.setParameter("stateCode", stateCode);
                query.setParameter("subCode", subCode);

                return query.list();
            }
        });

        if (results.size() < 1) {
            return null;
        }

        return results.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<BorderPoint> getByStateCodeAndSubCode(final StateCode stateCode, final SubCode subCode) {

        return (List<BorderPoint>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                String hql = "from BorderPoint bp where bp.stateCode = :stateCode and bp.subCode = :subCode";
                Query query = session.createQuery(hql);
                query.setParameter("stateCode", stateCode);
                query.setParameter("subCode", subCode);

                return query.list();
            }
        });
    }

    public Float getMaximumLatitude(StateCode stateCode, SubCode subCode) {

        String hql = "select max(bp.latitude) from BorderPoint bp where bp.stateCode = :stateCode and bp.subCode = :subCode";
        Query query = getSession().createQuery(hql);
        query.setParameter("stateCode", stateCode);
        query.setParameter("subCode", subCode);

        return (Float) query.uniqueResult();
    }

    public Float getMinimumLongitude(StateCode stateCode, SubCode subCode) {
        String hql = "select min(bp.longitude) from BorderPoint bp where bp.stateCode = :stateCode and bp.subCode = :subCode";
        Query query = getSession().createQuery(hql);
        query.setParameter("stateCode", stateCode);
        query.setParameter("subCode", subCode);

        return (Float) query.uniqueResult();
    }

    public Float getMaximumLongitude(StateCode stateCode, SubCode subCode) {
        String hql = "select max(bp.longitude) from BorderPoint bp where bp.stateCode = :stateCode and bp.subCode = :subCode";
        Query query = getSession().createQuery(hql);
        query.setParameter("stateCode", stateCode);
        query.setParameter("subCode", subCode);

        return (Float) query.uniqueResult();
    }
}
