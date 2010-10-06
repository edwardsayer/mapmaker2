package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.CustomFeature;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public interface CustomFeatureService extends BasicServiceOperations<CustomFeature>{

    void saveAll(List<CustomFeature> featureList);

    List<CustomFeature> getCustomFeatures(float minLat, float maxLat, float minLng, float maxLng, List<String> featureTypeList);

    List<String> getCustomFeatureTypes(float minLat, float maxLat, float minLng, float maxLng);
}
