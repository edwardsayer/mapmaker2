package org.jason.mapmaker2.dao;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public interface BorderPointDao extends GenericDao<BorderPoint> {

    List<BorderPoint> getByStateCodeAndSubCode(StateCode stateCode, SubCode subCode);

    Float getMinimumLatitude(StateCode stateCode, SubCode subCode);
}
