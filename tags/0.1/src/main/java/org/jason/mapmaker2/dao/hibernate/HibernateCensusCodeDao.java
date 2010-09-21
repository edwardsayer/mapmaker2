package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.CensusCodeDao;
import org.jason.mapmaker2.model.CensusCode;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("censusCodeDao")
public class HibernateCensusCodeDao extends HibernateGenericDao<CensusCode> implements CensusCodeDao {

    public HibernateCensusCodeDao() {
        super(CensusCode.class);
    }
}
