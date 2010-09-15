package org.jason.mapmaker2.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jason Ferguson
 */
public class Map implements Serializable {

    private CensusCode censusCode;
    private List<Location> locations;
    private List<Line> border;
    private Float centerLat;
    private Float centerLng;

    public CensusCode getCensusCode() {
        return censusCode;
    }

    public void setCensusCode(CensusCode censusCode) {
        this.censusCode = censusCode;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Line> getBorder() {
        return border;
    }

    public void setBorder(List<Line> border) {
        this.border = border;
    }

    public Float getCenterLat() {
        return centerLat;
    }

    public void setCenterLat(Float centerLat) {
        this.centerLat = centerLat;
    }

    public Float getCenterLng() {
        return centerLng;
    }

    public void setCenterLng(Float centerLng) {
        this.centerLng = centerLng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        if (!censusCode.equals(map.censusCode)) return false;
        if (!centerLat.equals(map.centerLat)) return false;
        if (!centerLng.equals(map.centerLng)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = censusCode.hashCode();
        result = 31 * result + centerLat.hashCode();
        result = 31 * result + centerLng.hashCode();
        return result;
    }
}
