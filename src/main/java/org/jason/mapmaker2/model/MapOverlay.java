package org.jason.mapmaker2.model;

import java.io.Serializable;
import java.util.List;

/**
 * MapOverlay.java
 *
 * Represents all the data necessary for the end user's KML file
 *
 * @author Jason Ferguson
 */
public class MapOverlay implements Serializable {

    private Integer id;
    private FeatureClass featureClass;
    private List<BorderPoint> borderpoints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FeatureClass getFeatureClass() {
        return featureClass;
    }

    public void setFeatureClass(FeatureClass featureClass) {
        this.featureClass = featureClass;
    }

    public List<BorderPoint> getBorderpoints() {
        return borderpoints;
    }

    public void setBorderpoints(List<BorderPoint> borderpoints) {
        this.borderpoints = borderpoints;
    }

    public String toKml() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapOverlay that = (MapOverlay) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
