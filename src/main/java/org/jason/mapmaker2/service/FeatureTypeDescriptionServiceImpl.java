package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.FeatureTypeDescriptionDao;
import org.jason.mapmaker2.model.FeatureTypeDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("featureTypeDescriptionService")
public class FeatureTypeDescriptionServiceImpl implements FeatureTypeDescriptionService {

    private FeatureTypeDescriptionDao featureTypeDescriptionDao;

    @Autowired
    public void setFeatureTypeDescriptionDao(FeatureTypeDescriptionDao featureTypeDescriptionDao) {
        this.featureTypeDescriptionDao = featureTypeDescriptionDao;
    }

    public FeatureTypeDescription save(FeatureTypeDescription obj) {

        return featureTypeDescriptionDao.save(obj);
    }

    public void update(FeatureTypeDescription obj) {

        featureTypeDescriptionDao.update(obj);
    }

    public void delete(FeatureTypeDescription obj) {
        featureTypeDescriptionDao.delete(obj);
    }

    public List<FeatureTypeDescription> getAll() {
        return featureTypeDescriptionDao.getAll();
    }

    public FeatureTypeDescription getById(int id) {
        return featureTypeDescriptionDao.getById(id);
    }
}
