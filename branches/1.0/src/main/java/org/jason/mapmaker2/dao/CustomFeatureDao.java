package org.jason.mapmaker2.dao;

import org.jason.mapmaker2.model.CustomFeature;

import java.util.List;
import java.util.Map;

/**
 * @author Jason Ferguson
 */
public interface CustomFeatureDao extends GenericDao<CustomFeature>{

    List<CustomFeature> getCustomFeatures(float minLat, float maxLat, float minLng, float maxLng, List<String> featureClassList);

    List<String> getCustomFeatureTypes(Map<String, Float> boundingBox);

    List<String> getCustomFeatureTypes(float minLat, float maxLat, float minLng, float maxLng);
}
