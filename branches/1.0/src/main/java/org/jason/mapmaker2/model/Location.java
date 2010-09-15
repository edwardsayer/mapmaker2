package org.jason.mapmaker2.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_LOCATIONS")
public class Location implements Serializable {

    private Integer id;
    private CensusCode censusCode;
    private String name;
    private Float lat;
    private Float lng;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!censusCode.equals(location.censusCode)) return false;
        if (!lat.equals(location.lat)) return false;
        if (!lng.equals(location.lng)) return false;
        if (!name.equals(location.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = censusCode.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lat.hashCode();
        result = 31 * result + lng.hashCode();
        return result;
    }
}
