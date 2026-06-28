package com.urlshortkaro.exception;

public class InvalidUrlException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidUrlException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}