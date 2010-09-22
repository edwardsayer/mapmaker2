package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Jason Ferguson
 */
@Entity
@Table(name = "T_TIGERFEATURETYPE")
public class TigerFeatureType implements Serializable, Comparable<TigerFeatureType> {

    private Integer id;
    private String tigerFeatureClassCode;
    private String description;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="CLASSCODE")
    public String getTigerFeatureClassCode() {
        return tigerFeatureClassCode;
    }

    public void setTigerFeatureClassCode(String tigerFeatureClassCode) {
        this.tigerFeatureClassCode = tigerFeatureClassCode;
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
        if (!(o instanceof TigerFeatureType)) return false;

        TigerFeatureType that = (TigerFeatureType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public int compareTo(TigerFeatureType o) {

        return this.getTigerFeatureClassCode().compareTo(o.getTigerFeatureClassCode());
    }
}
