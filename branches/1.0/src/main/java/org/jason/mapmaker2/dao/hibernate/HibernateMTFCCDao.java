package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.MTFCCDao;
import org.jason.mapmaker2.model.MTFCC;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("MTFCCDao")
public class HibernateMTFCCDao extends HibernateGenericDao<MTFCC> implements MTFCCDao{

    public HibernateMTFCCDao() {
        super(MTFCC.class);
    }
}
