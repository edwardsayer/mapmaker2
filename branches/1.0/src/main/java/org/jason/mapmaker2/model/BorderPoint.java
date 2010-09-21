package org.jason.mapmaker2.model;

import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
public class BorderPoint implements Serializable {

    private Integer id;
    private State state;
    private TigerFeatureType tigerFeatureType;
    private Float lat;
    private Float lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public TigerFeatureType getTigerFeatureType() {
        return tigerFeatureType;
    }

    public void setTigerFeatureType(TigerFeatureType tigerFeatureType) {
        this.tigerFeatureType = tigerFeatureType;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorderPoint that = (BorderPoint) o;

        if (!lat.equals(that.lat)) return false;
        if (!lng.equals(that.lng)) return false;
        if (!state.equals(that.state)) return false;
        if (tigerFeatureType != that.tigerFeatureType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + tigerFeatureType.hashCode();
        result = 31 * result + lat.hashCode();
        result = 31 * result + lng.hashCode();
        return result;
    }
}
