package com.urlshortkaro.exception;

public enum ErrorCode {

    INVALID_URL("URL001", "Invalid URL"),
    URL_EXPIRED("URL002", "URL has expired"),
    URL_NOT_FOUND("URL003", "URL not found"),
    SHORT_CODE_GENERATION_FAILED("URL004", "Unable to generate unique short code"),
    INVALID_ALIAS("URL005","Invalid alias"),
    ALIAS_ALREADY_EXISTS("URL006", "Alias already exists");

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() { return code; }

    public String getMessage() { return message; }
}