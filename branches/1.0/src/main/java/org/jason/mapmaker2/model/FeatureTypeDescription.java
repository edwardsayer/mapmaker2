package org.jason.mapmaker2.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
public class FeatureTypeDescription implements Serializable, Comparable<FeatureTypeDescription> {

    private Integer id;
    private String featureTypeName;
    private String featureClassCode;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="TYPENAME")
    public String getFeatureTypeName() {
        return featureTypeName;
    }

    public void setFeatureTypeName(String featureTypeName) {
        this.featureTypeName = featureTypeName;
    }

    @Column(name="CLASSCODE")
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

    public int compareTo(FeatureTypeDescription o) {

        return this.getFeatureClassCode().compareTo(o.getFeatureClassCode());
    }
}
