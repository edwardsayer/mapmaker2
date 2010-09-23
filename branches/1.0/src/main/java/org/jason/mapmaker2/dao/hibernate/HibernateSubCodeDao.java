package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.SubCodeDao;
import org.jason.mapmaker2.model.SubCode;

/**
 * @author Jason Ferguson
 */
public class HibernateSubCodeDao extends HibernateGenericDao<SubCode> implements SubCodeDao {

    public HibernateSubCodeDao() {
        super(SubCode.class);
    }
}
