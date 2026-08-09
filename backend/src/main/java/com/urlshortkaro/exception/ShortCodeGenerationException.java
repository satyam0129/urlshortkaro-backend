package com.urlshortkaro.exception;

public class ShortCodeGenerationException extends RuntimeException {

    private final ErrorCode errorCode;

    public ShortCodeGenerationException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}