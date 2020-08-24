package com.iit.spring.learning.exception;

public class TradierAppException extends Exception {
    private int errorCode;

    public TradierAppException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
