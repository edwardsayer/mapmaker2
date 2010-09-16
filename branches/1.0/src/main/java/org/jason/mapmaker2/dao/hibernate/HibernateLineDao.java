package org.jason.mapmaker2.dao.hibernate;

import org.jason.mapmaker2.dao.LineDao;
import org.jason.mapmaker2.model.Line;
import org.springframework.stereotype.Repository;

/**
 * @author Jason Ferguson
 */
@Repository("lineDao")
public class HibernateLineDao extends HibernateGenericDao<Line> implements LineDao {

        public HibernateLineDao() {
            super(Line.class);
        }
}
