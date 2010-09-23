package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_SUBCODE")
public class SubCode implements Serializable {

    private Integer id;
    private StateCode stateCode;
    private String subcodeDescription;
    private Integer code;

    @Id
    @Column(name = "ID")
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

    @Column(name="DESCRIPTION")
    public String getSubcodeDescription() {
        return subcodeDescription;
    }

    public void setSubcodeDescription(String subcodeDescription) {
        this.subcodeDescription = subcodeDescription;
    }

    @Column(name="CODE")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
