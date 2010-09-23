package org.jason.mapmaker2.model;

import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
public class SubCode implements Serializable {

    private Integer id;
    private StateCode stateCode;
    private String subcodeDescription;
    private Integer code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    public String getSubcodeDescription() {
        return subcodeDescription;
    }

    public void setSubcodeDescription(String subcodeDescription) {
        this.subcodeDescription = subcodeDescription;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
