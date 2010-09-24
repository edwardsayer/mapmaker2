package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.BorderPoint;

import java.util.Collection;

/**
 * @author Jason Ferguson
 */
public interface BorderPointService extends BasicServiceOperations<BorderPoint>{

    void saveAll(Collection<BorderPoint> bpCollection);
}
