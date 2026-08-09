package com.urlshortkaro.exception;

public class InvalidAliasException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidAliasException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}