package org.jason.mapmaker2.dao;

import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

import java.util.List;

/**
 * @author Jason Ferguson
 */
public interface SubCodeDao extends GenericDao<SubCode>{

        List<String> getUniqueSubCodeTypesByStateCode(StateCode stateCode);
}
