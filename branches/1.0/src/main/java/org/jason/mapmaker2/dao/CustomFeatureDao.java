package org.jason.mapmaker2.dao;

import org.jason.mapmaker2.model.CustomFeature;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public interface CustomFeatureDao extends GenericDao<CustomFeature>{

    List<CustomFeature> getCustomFeaturesWithBounds(float minLat, float maxLat, float minLng, float maxLng);
}
