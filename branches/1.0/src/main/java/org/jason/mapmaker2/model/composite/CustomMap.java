package org.jason.mapmaker2.model.composite;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.CustomFeature;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * CustomMap.java
 *
 * @author Jason Ferguson
 */
public class CustomMap implements Serializable {

    private Map<String, Float> boundingBox;
    private List<BorderPoint> borderPoints;
    private StateCode stateCode;
    private SubCode subCode;
    private List<CustomFeature> customFeatures;

    public CustomMap() {

    }

    public CustomMap(Float minLat, Float maxLat, Float minLng, Float maxLng) {

        boundingBox.put("minLat", minLat);
        boundingBox.put("maxLat", maxLat);
        boundingBox.put("minLng", minLng);
        boundingBox.put("maxLng", maxLng);
    }

    public Map<String, Float> getBoundingBox() {
        return boundingBox;
    }

    public void setBoundingBox(Map<String, Float> boundingBox) {
        this.boundingBox = boundingBox;
    }

    public List<BorderPoint> getBorderPoints() {
        return borderPoints;
    }

    public void setBorderPoints(List<BorderPoint> borderPoints) {
        this.borderPoints = borderPoints;
    }

    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    public SubCode getSubCode() {
        return subCode;
    }

    public void setSubCode(SubCode subCode) {
        this.subCode = subCode;
    }

    public List<CustomFeature> getCustomFeatures() {
        
        return customFeatures;
    }

    public void setCustomFeatures(List<CustomFeature> customFeatures) {
        this.customFeatures = customFeatures;
    }
}
