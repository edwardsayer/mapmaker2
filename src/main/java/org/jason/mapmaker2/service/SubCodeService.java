package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public interface SubCodeService extends BasicServiceOperations<SubCode>{

    List<SubCode> getByStateCode(StateCode stateCode);

    List<SubCode> getByStateCodeAndFeatureType(StateCode stateCode, String featureType);

    List<String> getUniqueSubCodeTypesByStateCode(StateCode stateCode);

}
