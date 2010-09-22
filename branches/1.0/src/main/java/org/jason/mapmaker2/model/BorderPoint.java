package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_BORDERPOINT")
public class BorderPoint implements Serializable {

    private Integer id;
    private State state;
    private TigerFeatureType tigerFeatureType;
    private Float latitude;
    private Float longitude;

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

    @ManyToOne
    @JoinColumn(name="FEATURETYPEID")
    public TigerFeatureType getTigerFeatureType() {
        return tigerFeatureType;
    }

    public void setTigerFeatureType(TigerFeatureType tigerFeatureType) {
        this.tigerFeatureType = tigerFeatureType;
    }

    @Column(name="LATITUDE")
    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Column(name="LONGITUDE")
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

        BorderPoint that = (BorderPoint) o;

        if (!latitude.equals(that.latitude)) return false;
        if (!longitude.equals(that.longitude)) return false;
        if (!state.equals(that.state)) return false;
        if (tigerFeatureType != that.tigerFeatureType) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + tigerFeatureType.hashCode();
        result = 31 * result + latitude.hashCode();
        result = 31 * result + longitude.hashCode();
        return result;
    }
}
