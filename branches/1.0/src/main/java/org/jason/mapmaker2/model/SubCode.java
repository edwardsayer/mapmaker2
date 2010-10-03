package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * SubCode.java
 *
 * Represents a subfeature of a state-based shapefile
 *
 * @author Jason Ferguson
 */
@Entity
@Table(name="T_SUBCODE")
@SuppressWarnings("unused")
public class SubCode implements Serializable {

    private Integer id;
    private StateCode stateCode;
    private Integer subCode;
    private String subCodeDescription;
    private String subCodeType;

    public SubCode() {

    }

    public SubCode(StateCode stateCode, Integer subCode, String subCodeDescription, String subCodeType) {
        this.stateCode = stateCode;
        this.subCode = subCode;
        this.subCodeDescription = subCodeDescription;
        this.subCodeType = subCodeType;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubCode subCode1 = (SubCode) o;

        if (stateCode != null ? !stateCode.equals(subCode1.stateCode) : subCode1.stateCode != null) return false;
        if (subCode != null ? !subCode.equals(subCode1.subCode) : subCode1.subCode != null) return false;
        if (subCodeDescription != null ? !subCodeDescription.equals(subCode1.subCodeDescription) : subCode1.subCodeDescription != null)
            return false;
        if (subCodeType != null ? !subCodeType.equals(subCode1.subCodeType) : subCode1.subCodeType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = stateCode != null ? stateCode.hashCode() : 0;
        result = 31 * result + (subCode != null ? subCode.hashCode() : 0);
        result = 31 * result + (subCodeDescription != null ? subCodeDescription.hashCode() : 0);
        result = 31 * result + (subCodeType != null ? subCodeType.hashCode() : 0);
        return result;
    }
}
