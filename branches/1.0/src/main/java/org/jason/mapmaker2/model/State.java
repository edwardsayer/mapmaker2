package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_STATE")
public class State implements Serializable {

    private Integer id;
    private String stateName;
    private String stateAbbr;
    private String stateFIPS;

    public State() {

    }

    public State(String stateName, String stateAbbr, String stateFIPS) {
        this.stateName = stateName;
        this.stateAbbr = stateAbbr;
        this.stateFIPS = stateFIPS;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Column(name="STATEFIPS")
    public String getStateFIPS() {
        return stateFIPS;
    }

    public void setStateFIPS(String stateFIPS) {
        this.stateFIPS = stateFIPS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        State state = (State) o;

        if (id != null ? !id.equals(state.id) : state.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
