package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.SubCodeDao;
import org.jason.mapmaker2.model.SubCode;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("subCodeDao")
public class HibernateSubCodeDao extends HibernateGenericDao<SubCode> implements SubCodeDao {

    public HibernateSubCodeDao() {
        super(SubCode.class);
    }
}
