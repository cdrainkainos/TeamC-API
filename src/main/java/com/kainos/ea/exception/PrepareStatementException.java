package com.kainos.ea.exception;

import java.sql.SQLException;

public class PrepareStatementException extends SQLException {
    @Override
    public String getMessage() {
        return "Unable to execute prepare statement" + super.getMessage();
    }
}
