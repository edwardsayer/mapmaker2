package org.jason.mapmaker2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;

/**
 * TLFeatureType.java
 * <p/>
 * Represents the feature type of a TIGER shapefile set (i.e. County, Congressional District, etc)
 * <p/>
 * This class is intended to represent metadata about a shapefile.
 *
 * @author Jason Ferguson
 */
@Entity
@Table(name = "T_TLFEATURETYPE")
@SuppressWarnings("unused")
public class TLFeatureType implements Serializable {

    private Integer id;
    private static final String url = "http://www2.census.gov/geo/tiger/TIGER2009/";
    private static final String prefix = "tl";
    private Integer year;
    private StateCode stateCode;
    private String postfix;
    private String description;
    private Boolean imported = false;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "YEAR")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @ManyToOne
    @JoinColumn(name = "STATECODEID")
    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    @Column(name = "POSTFIX")
    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "IMPORTED")
    public Boolean getImported() {
        return imported;
    }

    public void setImported(Boolean imported) {
        this.imported = imported;
    }

    @Transient
    public String getFormattedStateCode() {
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMinimumIntegerDigits(2);

        return formatter.format(stateCode.getStateCode());
    }

    @Transient
    public String getImportUrl() {
        return url + getFormattedStateCode() + "_" + stateCode.getStateName().toUpperCase() + "/"
                + prefix + "_" + year + "_" + getFormattedStateCode() + "_" + postfix + ".zip";
    }

    @Transient
    public String getFilename() {
        return prefix + "_" + year + "_" + getFormattedStateCode() + "_" + postfix + ".zip";
    }

    @Transient
    public String getRawFilename() {
        return prefix + "_" + year + "_" + getFormattedStateCode() + "_" + postfix;

    }
}
