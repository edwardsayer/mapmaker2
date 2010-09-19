package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.Location;

/**
 * @author Jason Ferguson
 */
public interface LocationService extends BasicServiceOperations<Location> {

    Location createLocation(Integer stateId, String name, String fipsCode, Integer featureClassId) throws ServiceException;
}
