package com.tenpo.calculator.model;

public class SumException extends RuntimeException {
    public SumException(String message) {
        super(message);
    }

    public SumException(String message, Throwable cause) {
        super(message, cause);
    }
}
