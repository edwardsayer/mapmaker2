package org.jason.mapmaker2.service;

import org.jason.mapmaker2.model.CustomMap;
import org.jason.mapmaker2.model.FeatureTypeDescription;
import org.jason.mapmaker2.model.State;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jason Ferguson
 */
public class CustomMapServiceImpl implements CustomMapService {

    private StateService stateService;
    private FeatureTypeDescriptionService featureTypeDescriptionService;

    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    @Autowired
    public void setFeatureTypeDescriptionService(FeatureTypeDescriptionService featureTypeDescriptionService) {
        this.featureTypeDescriptionService = featureTypeDescriptionService;
    }

    public CustomMap createCustomMap(Integer stateId, Integer featureTypeId) {

        CustomMap map = new CustomMap();

        State state = stateService.getById(stateId);
        map.setState(state);

        FeatureTypeDescription ftd = featureTypeDescriptionService.getById(featureTypeId);
        map.setFeatureTypeDescription(ftd);
        

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
