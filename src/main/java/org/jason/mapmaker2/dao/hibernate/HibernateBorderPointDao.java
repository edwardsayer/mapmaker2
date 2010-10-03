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
import java.util.List;

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
