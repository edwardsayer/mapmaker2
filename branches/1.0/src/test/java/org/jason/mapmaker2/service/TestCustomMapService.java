package org.jason.mapmaker2.service;

import junit.framework.TestCase;
import org.jason.mapmaker2.model.composite.CustomMap;
import org.junit.Test;

/**
 * @author Jason Ferguson
 */
public class TestCustomMapService extends TestCase {

    private CustomMapService customMapService = new CustomMapServiceImpl();

    @Test
    public void createMapShouldThrowAnExceptionWithAnInvalidStateCode() {

        try {
            CustomMap customMap = customMapService.createMap(null, null, null);
            fail("Invalid state code should throw a Service Exception");
        } catch (ServiceException e) {
        }

    }

    @Test
    public void createMapShouldThrowAnExceptionWithAnInvalidSubCode() {

        try {
            CustomMap customMap = customMapService.createMap(null, null, null);
            fail("Invalid subcode should throw a ServiceException");
        } catch (ServiceException e) {
        }
    }
}
