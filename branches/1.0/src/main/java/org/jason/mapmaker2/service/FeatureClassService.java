package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.FeatureClassDao;
import org.jason.mapmaker2.model.FeatureClass;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public class FeatureClassService implements BasicServiceOperations<FeatureClass> {

    private FeatureClassDao featureClassDao;

    @Autowired
    public void setFeatureClassDao(FeatureClassDao featureClassDao) {
        this.featureClassDao = featureClassDao;
    }

    public FeatureClass save(FeatureClass obj) {
        return featureClassDao.save(obj);
    }

    public void update(FeatureClass obj) {
        featureClassDao.update(obj);
    }

    public void delete(FeatureClass obj) {
        featureClassDao.delete(obj);
    }

    public List<FeatureClass> getAll() {
        return featureClassDao.getAll();
    }

    public FeatureClass getById(int id) {
        return featureClassDao.getById(id);
    }
}
