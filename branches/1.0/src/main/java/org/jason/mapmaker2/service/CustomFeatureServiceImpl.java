package org.jason.mapmaker2.service;

import org.jason.mapmaker2.dao.CustomFeatureDao;
import org.jason.mapmaker2.model.CustomFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public List<CustomFeature> getCustomFeatures(Map<String, Float> boundingBox, List<String> featureTypeList) {
        return customFeatureDao.getCustomFeatures(boundingBox, featureTypeList);
    }

    public List<CustomFeature> getCustomFeatures(float minLat, float maxLat, float minLng, float maxLng, List<String> featureTypeList) {
        return customFeatureDao.getCustomFeatures(minLat, maxLat, minLng, maxLng, featureTypeList);
    }

    public List<String> getCustomFeatureTypes(Map<String, Float> boundingBox) throws ServiceException {

        if (!boundingBox.containsKey("minLat")) {
            throw new ServiceException("Bounding box doesn't contain minimum latitude!");
        }

        if (!boundingBox.containsKey("maxLat")) {
            throw new ServiceException("Bounding box doesn't contain maximum latitude!");
        }

        if (!boundingBox.containsKey("minLng")) {
            throw new ServiceException("Bounding box doesn't contain minimum longitude!");
        }

        if (!boundingBox.containsKey("maxLng")) {
            throw new ServiceException("Bounding box doesn't contain maximum longitude!");
        }

        return customFeatureDao.getCustomFeatureTypes(boundingBox);
    }

    public List<String> getCustomFeatureTypes(float minLat, float maxLat, float minLng, float maxLng) {
        return customFeatureDao.getCustomFeatureTypes(minLat, maxLat, minLng, maxLng);
    }
}
