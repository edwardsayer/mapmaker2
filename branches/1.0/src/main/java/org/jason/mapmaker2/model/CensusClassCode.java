package org.jason.mapmaker2.model;

/**
 * @author Jason Ferguson
 */
public enum CensusClassCode {

    A("Airport"),
    C("Incorporated Place"),
    H("County Or Equivalent"),
    U("Populated Place");

    private CensusClassCode(String d) {
        description = d;
    }

    String description;

    public String getDescription() {
        return description;
    }
}
