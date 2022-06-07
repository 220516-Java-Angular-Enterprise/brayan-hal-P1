package com.revature.util.exceptions;

import java.sql.SQLException;

public class InvalidSQLException extends RuntimeException {
    public InvalidSQLException() {
        super();
    }

    public InvalidSQLException(String message) {
        super(message);
    }
}
