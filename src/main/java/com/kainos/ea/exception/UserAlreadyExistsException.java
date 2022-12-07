package com.kainos.ea.exception;

public class UserAlreadyExistsException extends Throwable{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
