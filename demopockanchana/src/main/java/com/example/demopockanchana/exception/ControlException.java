package com.example.demopockanchana.exception;

public class ControlException extends Exception {
    private String errorCode;
    private String message;

    public ControlException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
