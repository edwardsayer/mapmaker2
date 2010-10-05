package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.CustomFeature;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;
import org.jason.mapmaker2.model.composite.CustomMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jason Ferguson
 */
@Service("customMapService")
public class CustomMapServiceImpl implements CustomMapService {

    private StateCodeService stateCodeService;
    private SubCodeService subCodeService;
    private BorderPointService borderPointService;
    private CustomFeatureService customFeatureService;

    @Autowired
    public void setStateCodeService(StateCodeService stateCodeService) {
        this.stateCodeService = stateCodeService;
    }

    @Autowired
    public void setSubCodeService(SubCodeService subCodeService) {
        this.subCodeService = subCodeService;
    }

    @Autowired
    public void setBorderPointService(BorderPointService borderPointService) {
        this.borderPointService = borderPointService;
    }

    @Autowired
    public void setCustomFeatureService(CustomFeatureService customFeatureService) {
        this.customFeatureService = customFeatureService;
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

        float minLat = borderPointService.getMinimumLatitude(stateCode, subCode);
        float maxLat = borderPointService.getMaximumLatitude(stateCode, subCode);
        float minLng = borderPointService.getMinimumLongitude(stateCode, subCode);
        float maxLng = borderPointService.getMaximumLongitude(stateCode, subCode);

        List<CustomFeature> customFeatures = customFeatureService.getCustomFeaturesWithBounds(minLat, maxLat, minLng, maxLng);

        CustomMap map = new CustomMap();
        map.setStateCode(stateCode);
        map.setSubCode(subCode);
        map.setBorderPoints(borderPoints);
        map.setMinLat(minLat);
        map.setMaxLat(maxLat);
        map.setMinLng(minLng);
        map.setMaxLng(maxLng);
        map.setCustomFeatures(customFeatures);
        return map;
    }


    protected Float[] getPolyX(List<BorderPoint> borderPointList) {
        List<Float> xList = new ArrayList<Float>();
        for (BorderPoint bp: borderPointList) {
            xList.add(bp.getLongitude());
        }

        return (Float[]) xList.toArray();
    }

    protected Float[] getPolyY(List<BorderPoint> borderPointList) {
        List<Float> yList = new ArrayList<Float>();
        for (BorderPoint bp: borderPointList) {
            yList.add(bp.getLatitude());
        }

        return (Float[]) yList.toArray();
    }

    protected boolean isBorderPointInPolygon(float x, float y, int polySides, float polyX[], float polyY[]) {

        int i, j = polySides - 1;
        boolean oddNodes = false;

        for (i = 0; i < polySides; i++) {
            if (polyY[i] < y && polyY[j] >= y
                    || polyY[j] < y && polyY[i] >= y) {
                if (polyX[i] + (y - polyY[i]) / (polyY[j] - polyY[i]) * (polyX[j] - polyX[i]) < x) {
                    oddNodes = !oddNodes;
                }
            }
            j = i;
        }

        return oddNodes;

    }
}
