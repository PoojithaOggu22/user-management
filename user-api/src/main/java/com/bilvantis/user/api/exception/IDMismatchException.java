package com.bilvantis.user.api.exception;

public class IDMismatchException extends RuntimeException{

    public IDMismatchException(String message){
        super(message);
    }

    public IDMismatchException(String message, Throwable throwable){
        super(message, throwable);
    }

}
