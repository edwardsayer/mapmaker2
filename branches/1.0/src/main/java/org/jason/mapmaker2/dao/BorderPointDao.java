package org.jason.mapmaker2.dao;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

import java.util.List;
import java.util.Map;

/**
 * @author Jason Ferguson
 */
public interface BorderPointDao extends GenericDao<BorderPoint> {

    List<BorderPoint> getByStateCodeAndSubCode(StateCode stateCode, SubCode subCode);

    Map<String, Float> getBoundingBox(StateCode stateCode, SubCode subCode);
    
    Float getMinimumLatitude(StateCode stateCode, SubCode subCode);

    Float getMaximumLatitude(StateCode stateCode, SubCode subCode);

    Float getMinimumLongitude(StateCode stateCode, SubCode subCode);

    Float getMaximumLongitude(StateCode stateCode, SubCode subCode);
}
