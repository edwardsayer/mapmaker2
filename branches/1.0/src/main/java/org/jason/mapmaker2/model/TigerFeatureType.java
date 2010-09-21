package org.jason.mapmaker2.model;

/**
 * @author Jason Ferguson
 */
public enum TigerFeatureType {

    COUNTY(1),
    CONGRESSIONALDISTRICT(2);

    private TigerFeatureType(int t) {
        type = 1;
    }
    private int type;

    public int getType() {
        return type;
    }
}
