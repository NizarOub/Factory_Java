package com.bankati.dao.exceptions;

public class DaoConfigException extends RuntimeException{
    public DaoConfigException(String message) {
        super(message);
    }
    public DaoConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
