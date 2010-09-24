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
    private Integer subCode;
    private String subCodeDescription;
    private String subCodeType;

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

    @Column(name="SUBCODE")
    public Integer getSubCode() {
        return subCode;
    }

    public void setSubCode(Integer subCode) {
        this.subCode = subCode;
    }

    @Column(name="SUBCODEDESCRIPTION")
    public String getSubCodeDescription() {
        return subCodeDescription;
    }

    public void setSubCodeDescription(String subCodeDescription) {
        this.subCodeDescription = subCodeDescription;
    }

    @Column(name="SUBCODETYPE")
    public String getSubCodeType() {
        return subCodeType;
    }

    public void setSubCodeType(String subCodeType) {
        this.subCodeType = subCodeType;
    }
}
