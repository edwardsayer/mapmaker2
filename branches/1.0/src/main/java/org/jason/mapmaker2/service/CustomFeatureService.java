package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.CustomFeature;

import java.util.List;
import java.util.Map;

/**
 * @author Jason Ferguson
 */
public interface CustomFeatureService extends BasicServiceOperations<CustomFeature> {

    void saveAll(List<CustomFeature> featureList);

    List<CustomFeature> getCustomFeatures(Map<String, Float> boundingBox, List<String> featureTypeList);

    List<CustomFeature> getCustomFeatures(float minLat, float maxLat, float minLng, float maxLng, List<String> featureTypeList);

    List<String> getCustomFeatureTypes(Map<String, Float> boundingBox) throws ServiceException;

    List<String> getCustomFeatureTypes(float minLat, float maxLat, float minLng, float maxLng);
}
