package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.LocationDao;
import org.jason.mapmaker2.model.Location;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("locationDao")
public class HibernateLocationDao extends HibernateGenericDao<Location> implements LocationDao {

    public HibernateLocationDao() {
        super(Location.class);
    }

}
