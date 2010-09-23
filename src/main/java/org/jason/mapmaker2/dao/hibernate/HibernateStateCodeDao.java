package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.StateCodeDao;
import org.jason.mapmaker2.model.StateCode;

/**
 * @author Jason Ferguson
 */
public class HibernateStateCodeDao extends HibernateGenericDao<StateCode> implements StateCodeDao {

    public HibernateStateCodeDao() {
        super(StateCode.class);
    }
}
