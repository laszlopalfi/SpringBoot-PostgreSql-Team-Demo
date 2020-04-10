package com.example.demo.exception;

public class UserRepositoryException extends Exception {

    public UserRepositoryException(final String message) {
        super(message);
    }

    public UserRepositoryException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
