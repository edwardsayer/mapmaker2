package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.CustomFeatureDao;
import org.jason.mapmaker2.model.CustomFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("customFeatureService")
public class CustomFeatureServiceImpl implements CustomFeatureService {

    private CustomFeatureDao customFeatureDao;

    @Autowired
    public void setCustomFeatureDao(CustomFeatureDao customFeatureDao) {
        this.customFeatureDao = customFeatureDao;
    }

    public CustomFeature save(CustomFeature obj) {

        return customFeatureDao.save(obj);
    }

    public void update(CustomFeature obj) {

        customFeatureDao.update(obj);
    }

    public void delete(CustomFeature obj) {
        customFeatureDao.delete(obj);
    }

    public List<CustomFeature> getAll() {
        return customFeatureDao.getAll();
    }

    public CustomFeature getById(int id) {
        return customFeatureDao.getById(id);
    }

    public void saveAll(List<CustomFeature> featureList) {
        customFeatureDao.saveAll(featureList);
    }

    public List<CustomFeature> getCustomFeatures(float minLat, float maxLat, float minLng, float maxLng) {
        return customFeatureDao.getCustomFeatures(minLat, maxLat, minLng, maxLng);
    }

    public List<CustomFeature> getCustomFeatures(float minLat, float maxLat, float minLng, float maxLng, List<String> featureTypeList) {
        return customFeatureDao.getCustomFeatures(minLat, maxLat, minLng, maxLng, featureTypeList);
    }
}
