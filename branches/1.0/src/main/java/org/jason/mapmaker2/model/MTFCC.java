package org.jason.mapmaker2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_MTFCC")
@SuppressWarnings("unused")
public class MTFCC implements Serializable {

    private Integer id;
    private String MTFCC;
    private String featureClass;
    private String featureClassDescription;

    @Id
    @Column(name="ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="MTFCC")
    public String getMTFCC() {
        return MTFCC;
    }

    public void setMTFCC(String MTFCC) {
        this.MTFCC = MTFCC;
    }

    @Column(name="FEATURECLASS")
    public String getFeatureClass() {
        return featureClass;
    }

    public void setFeatureClass(String featureClass) {
        this.featureClass = featureClass;
    }

    @Column(name="FEATURECLASSDESC")
    public String getFeatureClassDescription() {
        return featureClassDescription;
    }

    public void setFeatureClassDescription(String featureClassDescription) {
        this.featureClassDescription = featureClassDescription;
    }
}
