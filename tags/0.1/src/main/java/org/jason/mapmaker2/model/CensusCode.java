package org.jason.mapmaker2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CensusCode.java
 *
 * Represents the CensusCode (formerly FIPS55) code
 * 
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_CENSUSCODE")
public class CensusCode {

    private Integer id;
    private Integer stateCode;
    private Integer countyCode;
    private String stateName;
    private String countyName;

    @Id
    @Column(name="ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="STATECODE")
    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    @Column(name="COUNTYCODE")
    public Integer getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(Integer countyCode) {
        this.countyCode = countyCode;
    }

    @Column(name="STATENAME")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Column(name="COUNTYNAME")
    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CensusCode that = (CensusCode) o;

        if (!countyCode.equals(that.countyCode)) return false;
        if (!stateCode.equals(that.stateCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stateCode.hashCode();
        result = 31 * result + countyCode.hashCode();
        return result;
    }
}
