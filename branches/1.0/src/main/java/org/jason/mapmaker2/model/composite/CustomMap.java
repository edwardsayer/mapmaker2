package org.jason.mapmaker2.model.composite;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.Location;
import org.jason.mapmaker2.model.State;
import org.jason.mapmaker2.model.TigerFeatureType;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jason Ferguson
 */
public class CustomMap implements Serializable {

    private State state;
    private TigerFeatureType featureType;
    private List<BorderPoint> borderPoints;
    private List<Location> locations;
    private String caption;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public TigerFeatureType getFeatureType() {
        return featureType;
    }

    public void setFeatureType(TigerFeatureType featureType) {
        this.featureType = featureType;
    }

    public List<BorderPoint> getBorderPoints() {
        return borderPoints;
    }

    public void setBorderPoints(List<BorderPoint> borderPoints) {
        this.borderPoints = borderPoints;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
