package com.bankati.dao.exceptions;

public class DaoException extends Throwable {
    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
