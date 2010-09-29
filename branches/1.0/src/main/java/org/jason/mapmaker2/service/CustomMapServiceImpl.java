package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.jason.mapmaker2.model.composite.CustomMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("customMapService")
public class CustomMapServiceImpl implements CustomMapService {

    private StateCodeService stateCodeService;
    private SubCodeService subCodeService;
    private BorderPointService borderPointService;

    @Autowired
    public StateCodeService getStateCodeService() {
        return stateCodeService;
    }

    @Autowired
    public SubCodeService getSubCodeService() {
        return subCodeService;
    }

    @Autowired
    public BorderPointService getBorderPointService() {
        return borderPointService;
    }

    public CustomMap createMap(Integer stateId, Integer subCodeId) throws ServiceException{

        if (stateId == null) {
            throw new ServiceException("Invalid State Code");
        }

        StateCode stateCode = stateCodeService.getById(stateId);

        if (subCodeId == null) {
            throw new ServiceException("Invalid SubCode");
        }
        SubCode subCode = subCodeService.getById(subCodeId);

        List<BorderPoint> borderPoints = borderPointService.getByStateCodeAndSubCode(stateCode, subCode);

        CustomMap map = new CustomMap();
        map.setStateCode(stateCode);
        map.setSubCode(subCode);
        map.setBorderPoints(borderPoints);

        return map;
    }
}
