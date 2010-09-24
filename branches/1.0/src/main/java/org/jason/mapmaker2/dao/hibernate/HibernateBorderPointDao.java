package org.jason.mapmaker2.dao.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.CacheMode;
import org.hibernate.Transaction;
import org.jason.mapmaker2.dao.BorderPointDao;
import org.jason.mapmaker2.model.BorderPoint;
import org.springframework.stereotype.Repository;

import java.util.Collection;

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

}
