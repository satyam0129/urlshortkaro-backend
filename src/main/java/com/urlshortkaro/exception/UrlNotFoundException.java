package com.urlshortkaro.exception;

public class UrlNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public UrlNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
