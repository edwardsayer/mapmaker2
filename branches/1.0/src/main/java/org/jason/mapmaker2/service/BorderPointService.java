package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

import java.util.Collection;
import java.util.List;

/**
 * @author Jason Ferguson
 */
public interface BorderPointService extends BasicServiceOperations<BorderPoint>{

    void saveAll(Collection<BorderPoint> bpCollection);

    List<BorderPoint> getByStateCodeAndSubCode(StateCode stateCode, SubCode subCode);
}
