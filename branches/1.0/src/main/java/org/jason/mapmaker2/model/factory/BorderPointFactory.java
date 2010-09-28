package org.jason.mapmaker2.model.factory;

import org.jason.mapmaker2.model.BorderPoint;
import org.jason.mapmaker2.model.StateCode;
import org.jason.mapmaker2.model.SubCode;

/**
 * @author TSgt Jason Ferguson
 */
public class BorderPointFactory {

    private StateCode stateCode;
    private SubCode subCode;
    //private BorderPoint borderPoint;

    public BorderPointFactory(StateCode stateCode, SubCode subCode) {
        this.stateCode = stateCode;
        this.subCode = subCode;
    }

    public BorderPoint getBorderPoint() {
        BorderPoint borderPoint = new BorderPoint();
        borderPoint.setStateCode(stateCode);
        borderPoint.setSubCode(subCode);

        return borderPoint;
    }
}
