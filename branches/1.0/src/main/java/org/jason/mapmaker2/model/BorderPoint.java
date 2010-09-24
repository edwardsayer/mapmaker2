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
    //private State state;
    private StateCode stateCode;
    //private TigerFeatureType tigerFeatureType;
    private Float latitude;
    private Float longitude;

    public BorderPoint() {

    }

    public BorderPoint(StateCode stateCode, Float latitude, Float longitude) {
        this.stateCode = stateCode;
        this.latitude = latitude;
        this.longitude = longitude;
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

//    @ManyToOne
//    @JoinColumn(name="STATEID")
//    public State getState() {
//        return state;
//    }
//
//    public void setState(State state) {
//        this.state = state;
//    }

    @ManyToOne
    @JoinColumn(name="STATECODEID")
    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }


//    @ManyToOne
//    @JoinColumn(name="TIGERFEATURETYPEID")
//    public TigerFeatureType getTigerFeatureType() {
//        return tigerFeatureType;
//    }
//
//    public void setTigerFeatureType(TigerFeatureType tigerFeatureType) {
//        this.tigerFeatureType = tigerFeatureType;
//    }

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
        if (!stateCode.equals(that.stateCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stateCode.hashCode();
        result = 31 * result + latitude.hashCode();
        result = 31 * result + longitude.hashCode();
        return result;
    }
}
