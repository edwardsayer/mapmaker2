package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.composite.CustomMap;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public interface CustomMapService {

    CustomMap createMap(Integer stateId, Integer subCodeId, List<String> featureTypes) throws ServiceException;
}
