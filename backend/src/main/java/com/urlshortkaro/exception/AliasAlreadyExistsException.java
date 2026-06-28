package com.urlshortkaro.exception;

public class AliasAlreadyExistsException extends RuntimeException {

    private final ErrorCode errorCode;

    public AliasAlreadyExistsException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}