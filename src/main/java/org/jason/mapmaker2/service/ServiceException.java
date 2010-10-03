package org.jason.mapmaker2.service;

/**
 * @author Jason Ferguson
 */
public class ServiceException extends Exception {

    public ServiceException() {
        super();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ServiceException(String message) {
        super(message);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);    //To change body of overridden methods use File | Settings | File Templates.
    }

    public ServiceException(Throwable cause) {
        super(cause);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
