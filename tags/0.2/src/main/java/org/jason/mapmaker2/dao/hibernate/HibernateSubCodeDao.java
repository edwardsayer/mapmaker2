package org.jason.mapmaker2.dao.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jason.mapmaker2.dao.SubCodeDao;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Jason Ferguson
 */
@Repository("subCodeDao")
public class HibernateSubCodeDao extends HibernateGenericDao<SubCode> implements SubCodeDao {

    public HibernateSubCodeDao() {
        super(SubCode.class);
    }


    @SuppressWarnings("unchecked")
    public List<String> getUniqueSubCodeTypesByStateCode(StateCode stateCode) {

        return (List<String>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                String hql = "select distinct sc.subCodeType from SubCode sc";
                Query query = session.createQuery(hql);
                return query.list();
            }
        });
    }

    @SuppressWarnings("unchecked")
    public List<String> getUniqueDescriptionsByFeatureType(final String featureType) {

        return (List<String>) getHibernateTemplate().execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                String hql = "select distinct sc.subCodeDescription from SubCode sc where sc.subCodeType = :type";
                Query query = session.createQuery(hql);
                query.setString("type", featureType);

                return query.list();
            }
        });
    }
}
