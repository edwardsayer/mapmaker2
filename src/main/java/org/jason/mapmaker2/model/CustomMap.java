package org.jason.mapmaker2.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jason Ferguson
 */
public class CustomMap implements Serializable {

    State state;
    FeatureTypeDescription featureTypeDescription;
    List<BorderPoint> borderPoints;
    List<Location> locations;
    String caption;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public FeatureTypeDescription getFeatureTypeDescription() {
        return featureTypeDescription;
    }

    public void setFeatureTypeDescription(FeatureTypeDescription featureTypeDescription) {
        this.featureTypeDescription = featureTypeDescription;
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

    public String toKML() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomMap customMap = (CustomMap) o;

        if (!featureTypeDescription.equals(customMap.featureTypeDescription)) return false;
        if (!state.equals(customMap.state)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + featureTypeDescription.hashCode();
        return result;
    }
}
