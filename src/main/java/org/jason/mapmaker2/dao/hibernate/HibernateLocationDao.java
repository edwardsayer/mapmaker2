package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.LocationDao;
import org.jason.mapmaker2.model.Location;

/**
 * @author Jason Ferguson
 */
public class HibernateLocationDao extends HibernateGenericDao<Location> implements LocationDao {

    public HibernateLocationDao() {
        super(Location.class);
    }
}
