package org.jason.mapmaker2.model;

import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
public class FeatureTypeDescription implements Serializable {

    private Integer id;
    private String featureTypeName;
    private String featureClassCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFeatureTypeName() {
        return featureTypeName;
    }

    public void setFeatureTypeName(String featureTypeName) {
        this.featureTypeName = featureTypeName;
    }

    public String getFeatureClassCode() {
        return featureClassCode;
    }

    public void setFeatureClassCode(String featureClassCode) {
        this.featureClassCode = featureClassCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureTypeDescription that = (FeatureTypeDescription) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
