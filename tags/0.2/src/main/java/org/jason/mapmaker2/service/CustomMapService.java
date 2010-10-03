package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.composite.CustomMap;

/**
 * @author Jason Ferguson
 */
public interface CustomMapService {

    CustomMap createMap(Integer stateId, Integer subCodeId) throws ServiceException;
}
