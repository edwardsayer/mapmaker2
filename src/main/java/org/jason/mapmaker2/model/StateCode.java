package org.jason.mapmaker2.model;

import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
public class StateCode implements Serializable, Comparable<StateCode> {

    private int id;
    private Integer stateCode;
    private String stateName;
    private String stateAbbr;

    public StateCode() {

    }

    public StateCode(String stateName, String stateAbbr, Integer stateCode) {
        this.stateCode = stateCode;
        this.stateName = stateName;
        this.stateAbbr = stateAbbr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StateCode stateCode = (StateCode) o;

        if (id != stateCode.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public int compareTo(StateCode o) {

        return this.stateName.compareTo(o.getStateName());
    }
}
