package org.training.service.exception;

/**
 * Created by nicko on 1/30/2017.
 */
public class ServiceException extends RuntimeException {

    public ServiceException() {
        super("Exception on service");
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
