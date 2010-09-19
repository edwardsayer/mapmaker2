package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.StateDao;
import org.jason.mapmaker2.model.State;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("stateDao")
public class HibernateStateDao extends HibernateGenericDao<State> implements StateDao {

    public HibernateStateDao() {
        super(State.class);
    }
}
