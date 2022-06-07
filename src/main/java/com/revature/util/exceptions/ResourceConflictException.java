package com.revature.util.exceptions;

public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException() {
        super();
    }

    public ResourceConflictException(String message) {
        super(message);
    }
}
