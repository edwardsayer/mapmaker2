package org.jason.mapmaker2.web.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ParameterAware;
import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.composite.CustomMap;
import org.jason.mapmaker2.service.CustomMapService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * CustomMapAction.java
 *
 * @author Jason Ferguson
 */
@ParentPackage("json-default")
@Namespace("/customMap")
@SuppressWarnings("unused")
public class CustomMapAction extends ActionSupport implements ParameterAware {

    private Map<String, String[]> parameters;

    public void setParameters(Map<String, String[]> parameters) {
        this.parameters = parameters;
    }

    private CustomMapService customMapService;

    @Autowired
    public void setCustomMapService(CustomMapService customMapService) {
        this.customMapService = customMapService;
    }

    private CustomMap map;

    private Integer stateId;
    private Integer subCodeId;
    private String featureName;
    private Float minLat;
    private Float maxLat;
    private Float minLng;
    private Float maxLng;
    
    private String caption;
    private List<BorderPoint> borderPoints;

    public CustomMap getMap() {
        return map;
    }

    public void setMap(CustomMap map) {
        this.map = map;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getSubCodeId() {
        return subCodeId;
    }

    public void setSubCodeId(Integer subCodeId) {
        this.subCodeId = subCodeId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public Float getMinLat() {
        return minLat;
    }

    public void setMinLat(Float minLat) {
        this.minLat = minLat;
    }

    public Float getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(Float maxLat) {
        this.maxLat = maxLat;
    }

    public Float getMinLng() {
        return minLng;
    }

    public void setMinLng(Float minLng) {
        this.minLng = minLng;
    }

    public Float getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(Float maxLng) {
        this.maxLng = maxLng;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<BorderPoint> getBorderPoints() {
        return borderPoints;
    }

    public void setBorderPoints(List<BorderPoint> borderPoints) {
        this.borderPoints = borderPoints;
    }

    public String execute() throws Exception {

        return SUCCESS;
    }

    @Action(value = "getCustomMapJson", results = {
            @Result(name="success", type = "json")
    })
    public String getCustomMapJson() throws Exception {

        stateId = Integer.parseInt(parameters.get("stateId")[0]);
        subCodeId = Integer.parseInt(parameters.get("subCodeId")[0]);

        String[] features = parameters.get("featureClasses")[0].split(",");
        
        // create the map with the borderpoints
        map = customMapService.createMap(stateId, subCodeId, Arrays.asList(features));

        return SUCCESS;
    }
}
