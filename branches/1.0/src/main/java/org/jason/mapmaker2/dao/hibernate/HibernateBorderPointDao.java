package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.BorderPointDao;
import org.jason.mapmaker2.model.BorderPoint;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("borderPointDao")
public class HibernateBorderPointDao extends HibernateGenericDao<BorderPoint> implements BorderPointDao {

    public HibernateBorderPointDao() {
        super(BorderPoint.class);
    }
}
