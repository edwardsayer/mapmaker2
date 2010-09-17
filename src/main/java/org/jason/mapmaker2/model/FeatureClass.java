package org.jason.mapmaker2.model;

import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
public class FeatureClass implements Serializable {

    private Integer id;
    private String classCode;
    private String classCodeShortDesc;
    private String classCodeDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassCodeShortDesc() {
        return classCodeShortDesc;
    }

    public void setClassCodeShortDesc(String classCodeShortDesc) {
        this.classCodeShortDesc = classCodeShortDesc;
    }

    public String getClassCodeDesc() {
        return classCodeDesc;
    }

    public void setClassCodeDesc(String classCodeDesc) {
        this.classCodeDesc = classCodeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeatureClass that = (FeatureClass) o;

        if (classCode != null ? !classCode.equals(that.classCode) : that.classCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return classCode != null ? classCode.hashCode() : 0;
    }
}
