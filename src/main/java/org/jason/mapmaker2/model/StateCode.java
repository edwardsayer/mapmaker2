package org.jason.mapmaker2.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_STATECODE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="STATECODE")
    public Integer getStateCode() {
        return stateCode;
    }

    public void setStateCode(Integer stateCode) {
        this.stateCode = stateCode;
    }

    @Column(name="STATENAME")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Column(name="STATEABBR")
    public String getStateAbbr() {
        return stateAbbr;
    }

    public void setStateAbbr(String stateAbbr) {
        this.stateAbbr = stateAbbr;
    }

    @Transient
    public String getLabel() {
        return stateName + " (" + stateCode + ")";    
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
