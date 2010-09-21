package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.CustomMap;

/**
 * @author Jason Ferguson
 */
public interface CustomMapService {

    CustomMap createCustomMap(Integer stateId, Integer featureTypeId);    
}
