package com.uni.project.exception;

public class NoUsersFoundException extends RuntimeException {

    public NoUsersFoundException() {
        super();
    }

    public NoUsersFoundException(String message) {
        super(message);
    }
}
