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
 * Maybe this would be better as an enum?
 * 
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_CENSUSCODE")
public class CensusCode {

    private int id;
    private String censusCode;
    private String description;

    @Id
    @Column(name="ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="CENSUSCODE")
    public String getCensusCode() {
        return censusCode;
    }

    public void setCensusCode(String censusCode) {
        this.censusCode = censusCode;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CensusCode that = (CensusCode) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
