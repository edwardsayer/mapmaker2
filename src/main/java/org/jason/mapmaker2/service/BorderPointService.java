package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Jason Ferguson
 */
public interface BorderPointService extends BasicServiceOperations<BorderPoint> {

    void saveAll(Collection<BorderPoint> bpCollection);

    List<BorderPoint> getByStateCodeAndSubCode(StateCode stateCode, SubCode subCode);

    Map<String, Float> getBoundingBox(Integer stateCodeId, Integer subCodeId);

    Map<String, Float> getBoundingBox(StateCode stateCode, SubCode subCode);

    Float getMinimumLatitude(Integer stateCodeId, Integer subCodeId);

    Float getMinimumLatitude(StateCode stateCode, SubCode subCode);

    Float getMaximumLatitude(Integer stateCodeId, Integer subCodeId);

    Float getMaximumLatitude(StateCode stateCode, SubCode subCode);

    Float getMinimumLongitude(Integer stateCodeId, Integer subCodeId);

    Float getMinimumLongitude(StateCode stateCode, SubCode subCode);

    Float getMaximumLongitude(Integer stateCodeId, Integer subCodeId);

    Float getMaximumLongitude(StateCode stateCode, SubCode subCode);
}
