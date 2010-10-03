package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * BorderPoint.java
 *
 * Represents a single point belonging to a State and a SubCode
 *
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_BORDERPOINT")
public class BorderPoint implements Serializable {

    private Integer id;
    private StateCode stateCode;
    private SubCode subCode;
    private Float latitude;
    private Float longitude;

    public BorderPoint() {

    }

    public BorderPoint(StateCode stateCode, SubCode subCode, Float latitude, Float longitude) {
        this.stateCode = stateCode;
        this.subCode = subCode;
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

    @ManyToOne
    @JoinColumn(name="STATECODEID")
    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    @ManyToOne
    @JoinColumn(name="SUBCODEID")
    public SubCode getSubCode() {
        return subCode;
    }

    public void setSubCode(SubCode subCode) {
        this.subCode = subCode;
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

        if (latitude != null ? !latitude.equals(that.latitude) : that.latitude != null) return false;
        if (longitude != null ? !longitude.equals(that.longitude) : that.longitude != null) return false;
        if (stateCode != null ? !stateCode.equals(that.stateCode) : that.stateCode != null) return false;
        if (subCode != null ? !subCode.equals(that.subCode) : that.subCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stateCode != null ? stateCode.hashCode() : 0;
        result = 31 * result + (subCode != null ? subCode.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}