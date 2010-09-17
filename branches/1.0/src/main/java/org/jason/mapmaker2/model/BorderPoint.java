package org.jason.mapmaker2.model;

import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
public class BorderPoint implements Serializable {

    private Integer id;
    private CensusCode stateCode;
    private CensusCode countyCode;
    private Float bpLat;
    private Float bpLng;
    private FeatureClass featureClass;

    public BorderPoint() {

    }

    public BorderPoint(CensusCode stateCode, CensusCode countyCode, Float bpLat, Float bpLng, FeatureClass featureClass) {
        this.stateCode = stateCode;
        this.countyCode = countyCode;
        this.bpLat = bpLat;
        this.bpLng = bpLng;
        this.featureClass = featureClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CensusCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(CensusCode stateCode) {
        this.stateCode = stateCode;
    }

    public CensusCode getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(CensusCode countyCode) {
        this.countyCode = countyCode;
    }

    public Float getBpLat() {
        return bpLat;
    }

    public void setBpLat(Float bpLat) {
        this.bpLat = bpLat;
    }

    public Float getBpLng() {
        return bpLng;
    }

    public void setBpLng(Float bpLng) {
        this.bpLng = bpLng;
    }

    public FeatureClass getFeatureClass() {
        return featureClass;
    }

    public void setFeatureClass(FeatureClass featureClass) {
        this.featureClass = featureClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorderPoint that = (BorderPoint) o;

        if (bpLat != null ? !bpLat.equals(that.bpLat) : that.bpLat != null) return false;
        if (bpLng != null ? !bpLng.equals(that.bpLng) : that.bpLng != null) return false;
        if (countyCode != null ? !countyCode.equals(that.countyCode) : that.countyCode != null) return false;
        if (stateCode != null ? !stateCode.equals(that.stateCode) : that.stateCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stateCode != null ? stateCode.hashCode() : 0;
        result = 31 * result + (countyCode != null ? countyCode.hashCode() : 0);
        result = 31 * result + (bpLat != null ? bpLat.hashCode() : 0);
        result = 31 * result + (bpLng != null ? bpLng.hashCode() : 0);
        return result;
    }
}
