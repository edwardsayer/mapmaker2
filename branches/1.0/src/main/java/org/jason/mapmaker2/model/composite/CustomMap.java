package org.jason.mapmaker2.model.composite;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jason Ferguson
 */
public class CustomMap implements Serializable {

    private Float minLat;
    private Float maxLat;
    private Float minLng;
    private Float maxLng;
    private List<BorderPoint> borderPoints;
    private StateCode stateCode;
    private SubCode subCode;

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
}
