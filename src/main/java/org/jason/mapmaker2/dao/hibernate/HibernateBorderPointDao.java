package org.jason.mapmaker2.dao.hibernate;

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

    public HibernateBorderPointDao() {
        super(BorderPoint.class);
    }

    @Override
    public void saveAll(Collection<BorderPoint> objList) {

        Transaction tx = getSession().beginTransaction();

        int counter = 0;
        for (BorderPoint bp : objList) {
            getSession().save(bp);
            counter++;
            if (counter == 200) {
                System.out.println("Commiting 50 BorderPoints");
                if (getSession().isDirty()) {
                    getSession().flush();
                    getSession().clear();
                }
                counter = 0;
                //tx.commit();
            }
        }

        tx.commit();
    }

}
