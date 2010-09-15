package org.jason.mapmaker2.model;

import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
public class Line implements Serializable {

    private Integer id;
    private CensusCode censusCode;
    private Float startLat;
    private Float startLng;
    private Float endLat;
    private Float endLng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CensusCode getCensusCode() {
        return censusCode;
    }

    public void setCensusCode(CensusCode censusCode) {
        this.censusCode = censusCode;
    }

    public Float getStartLat() {
        return startLat;
    }

    public void setStartLat(Float startLat) {
        this.startLat = startLat;
    }

    public Float getStartLng() {
        return startLng;
    }

    public void setStartLng(Float startLng) {
        this.startLng = startLng;
    }

    public Float getEndLat() {
        return endLat;
    }

    public void setEndLat(Float endLat) {
        this.endLat = endLat;
    }

    public Float getEndLng() {
        return endLng;
    }

    public void setEndLng(Float endLng) {
        this.endLng = endLng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (endLat != null ? !endLat.equals(line.endLat) : line.endLat != null) return false;
        if (endLng != null ? !endLng.equals(line.endLng) : line.endLng != null) return false;
        if (startLat != null ? !startLat.equals(line.startLat) : line.startLat != null) return false;
        if (startLng != null ? !startLng.equals(line.startLng) : line.startLng != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startLat != null ? startLat.hashCode() : 0;
        result = 31 * result + (startLng != null ? startLng.hashCode() : 0);
        result = 31 * result + (endLat != null ? endLat.hashCode() : 0);
        result = 31 * result + (endLng != null ? endLng.hashCode() : 0);
        return result;
    }
}
