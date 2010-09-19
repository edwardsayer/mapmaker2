package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_LOCATION")
public class Location implements Serializable {

    private Integer id;
    private State state;
    private String locationName;
    private String locationFIPS;
    private FeatureClass featureClass;

    public Location() {

    }

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="STATEID")
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Column(name="LOCATIONNAME")
    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Column(name="LOCATIONFIPS")
    public String getLocationFIPS() {
        return locationFIPS;
    }

    public void setLocationFIPS(String locationFIPS) {
        this.locationFIPS = locationFIPS;
    }

    @ManyToOne
    @JoinColumn(name="FEATURECLASSID")
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

        Location location = (Location) o;

        if (id != null ? !id.equals(location.id) : location.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
