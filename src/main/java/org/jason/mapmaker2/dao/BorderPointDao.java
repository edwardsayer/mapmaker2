package org.jason.mapmaker2.dao;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

/**
 * @author Jason Ferguson
 */
public interface BorderPointDao extends GenericDao<BorderPoint> {

    Float getMinimumLatitude(StateCode stateCode, SubCode subCode);
}
