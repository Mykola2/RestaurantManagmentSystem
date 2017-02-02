package org.training.dao.exception;

/**
 * Created by nicko on 1/29/2017.
 */
public class DAOException extends RuntimeException {

    public DAOException() {
        super("Exception in DAO");
    }

    public DAOException(Exception e) {
        super("Exception in DAO",e);
    }

}
