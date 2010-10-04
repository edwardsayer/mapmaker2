package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name = "T_CUSTOMFEATURE")
public class CustomFeature implements Serializable {

    private Integer featureId;
    private String featureName;
    private String featureClass;
    private StateCode stateCode;
    private Float latitude;
    private Float longitude;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    @Column(name = "FEATURENAME")
    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @Column(name = "FEATURECLASS")
    public String getFeatureClass() {
        return featureClass;
    }

    public void setFeatureClass(String featureClass) {
        this.featureClass = featureClass;
    }

    @ManyToOne
    @JoinColumn(name = "STATECODEID")
    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    @Column(name = "LATITUDE")
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Column(name = "LONGITUDE")
    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomFeature feature = (CustomFeature) o;

        if (featureClass != null ? !featureClass.equals(feature.featureClass) : feature.featureClass != null)
            return false;
        if (featureName != null ? !featureName.equals(feature.featureName) : feature.featureName != null) return false;
        if (latitude != null ? !latitude.equals(feature.latitude) : feature.latitude != null) return false;
        if (longitude != null ? !longitude.equals(feature.longitude) : feature.longitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = featureName != null ? featureName.hashCode() : 0;
        result = 31 * result + (featureClass != null ? featureClass.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
