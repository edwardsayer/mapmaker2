package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.CensusCodeDao;
import org.jason.mapmaker2.model.CensusCode;

/**
 * @author Jason Ferguson
 */
public class HibernateCensusCodeDao extends HibernateGenericDao<CensusCode> implements CensusCodeDao {

    public HibernateCensusCodeDao() {
        super(CensusCode.class);
    }
}
