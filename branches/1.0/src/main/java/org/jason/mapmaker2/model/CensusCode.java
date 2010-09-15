package org.jason.mapmaker2.model;

/**
 * @author Jason Ferguson
 */
public class CensusCode {

    private int id;
    private String censusCode;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCensusCode() {
        return censusCode;
    }

    public void setCensusCode(String censusCode) {
        this.censusCode = censusCode;
    }

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
